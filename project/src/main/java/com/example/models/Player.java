package com.example.models;

import com.example.models.NPCModels.NPCFriendship;
import com.example.models.enums.types.BackpackType;
import com.example.models.enums.types.TrashcanType;
import com.example.models.items.Item;
import com.example.models.mapModels.Coordinate;
import com.example.models.mapModels.Farm;
import com.example.models.skills.Skill;

import java.util.ArrayList;

public class Player {
    private Coordinate coordinate;
    private int money;
    private Backpack inventory;
    private  Farm farm;
    private final ArrayList<Skill> skills = new ArrayList<>();
    private final ArrayList<Quest> quests = new ArrayList<>();
    private final User user;
    private final ArrayList<Friendship> friendships = new ArrayList<>();
    private final ArrayList<NPCFriendship> npcFriendships = new ArrayList<>();
    private final ArrayList<PlayerAnimal> animals = new ArrayList<>();
    private double energy = 0;
    private TrashcanType trashcanType;
    private Item equippedItem;

    /// TODO: called in game thread.
    public void checkForFainting() {

    }
    public Player(User user) {
        this.user = user;
        this.inventory = new Backpack(BackpackType.DEFAULT);
        this.trashcanType = TrashcanType.DEFAULT;
    }

    public Player(Coordinate coordinate, int money, Farm farm, User user, double energy) {
        this.coordinate = coordinate;
        this.trashcanType = TrashcanType.DEFAULT;
        this.inventory = new Backpack(BackpackType.DEFAULT);
        this.money = money;
        this.farm = farm;
        this.user = user;
        this.energy = energy;
    }

    public double getEnergy() {
        return energy;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Backpack getInventory() {
        return inventory;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Friendship> getFriendships() {
        return friendships;
    }

    public ArrayList<NPCFriendship> getNpcFriendships() {
        return npcFriendships;
    }

    public TrashcanType getTrashcanType() {
        return trashcanType;
    }

    public void setTrashcanType(TrashcanType trashcanType) {
        this.trashcanType = trashcanType;
    }

    public ArrayList<PlayerAnimal> getAnimals() {
        return animals;
    }


    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }
}
