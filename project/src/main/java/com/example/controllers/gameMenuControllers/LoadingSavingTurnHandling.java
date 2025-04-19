package com.example.controllers.gameMenuControllers;

import com.example.Repositories.GameRepository;
import com.example.Repositories.UserRepository;
import com.example.controllers.Controller;
import com.example.models.App;
import com.example.models.Game;
import com.example.models.IO.Request;
import com.example.models.IO.Response;
import com.example.models.Player;
import com.example.models.User;
import com.example.models.enums.types.MenuTypes;
import com.example.models.mapModels.Farm;

import java.util.ArrayList;

public class LoadingSavingTurnHandling extends Controller {
    public static boolean isWaitingForChoosingMap = false;

    public static Response handleNewGame(Request request) {
        String[] usernames = request.body.get("users").split("\\s+");

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(App.getLoggedInUser()));
        for (int i = 1; i < usernames.length; i++) {
            String username = usernames[i];
            User user = UserRepository.findUserByUsername(username);
            if (user == null) {
                return new Response(false, "User not found");
            }
            if (user.getCurrentGame() != null) {
                return new Response(false, "Player " + username + " is already in a game");
            }
            Player player = new Player(user);
            players.add(player);
        }
        Game game = new Game(players, players.get(0));
        for (Player player : players) {
            player.getUser().setCurrentGame(game);
            player.getUser().getGames().add(game);
            player.getUser().setNumberOfGames(player.getUser().getNumberOfGames() + 1);
        }
        isWaitingForChoosingMap = true;
        return new Response(true, "The game has been made successfully. Awaiting each user's map choice...");
    }

    public static Response handleMapSelection(Request request) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        int mapNumber = Integer.parseInt(request.body.get("mapNumber"));
        if (mapNumber != 1 && mapNumber != 2) {
            return new Response(false, "Invalid map number");
        }
        Farm farm = Farm.makeFarm(mapNumber);
        game.getMap().addFarm(farm);
        player.setFarm(farm);
        boolean check = game.cycleToNextPlayer();
        if (check) {
            isWaitingForChoosingMap = false;
        }
        String responseString = player.getUser().getUsername() + " has chosen their farm.";
        if (check) {
            responseString += "\nAll farm selection successful! Game successfully created!";
        }
        return new Response(true, responseString);
    }

    public static Response handleLoadGame(Request request) {
        //TODO: Ensure that 'currentGame' is correctly loaded from database onto user.
        if (App.getLoggedInUser().getCurrentGame() == null) {
            return new Response(false, "No saved game found.");
        }
        User user = App.getLoggedInUser();
        Game game = App.getLoggedInUser().getCurrentGame();
        ArrayList<Player> players = game.getPlayers();
        Player firstPlayer = players.getFirst();
        Player loader = null;
        for (Player player : players) {
            if (player.getUser() == user) {
                game.setCurrentPlayer(player);
                loader = player;
                break;
            }
        }
        game.setGameOngoing(true);
        int loaderIndex = players.indexOf(loader);
        players.set(0, loader);
        players.set(loaderIndex, firstPlayer);
        return new Response(true, "The game has been loaded successfully. Welcome "
                + user.getUsername());
    }

    public static Response handleExitGame(Request request) {
        if (App.getLoggedInUser().getCurrentGame()
                .getCurrentPlayer().getUser() == App.getLoggedInUser()) {
            App.getLoggedInUser().getCurrentGame().setGameOngoing(false);
            App.getLoggedInUser().getCurrentGame().hasTurnCycleFinished = false;
            GameRepository.saveGame(App.getLoggedInUser().getCurrentGame());
            App.setCurrMenuType(MenuTypes.GameMenu);

            return new Response(true, "Exiting and saving game. Redirecting to game menu...");
        } else {
            return new Response(false, "Only the logged in user may exit the game.");
        }
    }

    public static Response handleForceDeleteGame(Request request) {
        //TODO: force deleting game entirely.
        return null;
    }

    public static Response handleNextTurn(Request request) {

        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();

        int numberOfPlayers = game.getPlayers().size();
        int playerIndex;

        game.getCurrentPlayer().setUsedEnergyInTurn(0);

        String responseString = "";
        do {
            playerIndex = game.getPlayers().indexOf(game.getCurrentPlayer());
            if (playerIndex == numberOfPlayers - 1) {
                game.setCurrentPlayer(game.getPlayers().getFirst());
                game.hasTurnCycleFinished = true;
            } else {
                game.setCurrentPlayer(game.getPlayers().get(playerIndex + 1));
            }

            if (game.getCurrentPlayer().isPlayerFainted()) {
                responseString +=
                        ("Player " + game.getCurrentPlayer().getUser().getUsername() + " was fainted and skipped.\n");
            }
        } while (game.getCurrentPlayer().isPlayerFainted());

        responseString += ("It is " + game.getCurrentPlayer().getUser().getUsername() + "'s turn now!");
        return new Response(true, responseString);
    }
}
