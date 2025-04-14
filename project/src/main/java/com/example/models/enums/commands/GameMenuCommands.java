package com.example.models.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {
    GAME_NEW("^game\\s+new\\s+-u(?<users>(\\s+\\S+){1, 3})$"),
    GAME_MAP("^game\\s+map\\s+(?<mapNumber>\\d+)$"),
    LOAD_GAME("^load\\s+game$"),
    EXIT_GAME("^exit\\s+game$"),
    NEXT_TURN("^next\\s+turn$"),
    TIME("^time$"),
    DATE("^date$"),
    DATETIME("^datetime$"),
    DAY_OF_WEEK("^day\\s+of\\s+the\\s+week$"),
    CHEAT_ADVANCE_TIME("^cheat\\s+advance\\s+time\\s+(?<X>\\d+)h$"),
    CHEAT_ADVANCE_DATE("^cheat\\s+advance\\s+date\\s+(?<X>\\d+)d$"),
    SEASON("^season$"),
    CHEAT_THOR("^cheat\\s+Thor\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)$"),
    WEATHER("^weather$"),
    WEATHER_FORECAST("^weather\\s+forecast$"),
    CHEAT_WEATHER_SET("^cheat\\s+weather\\s+set\\s+(?<Type>.+)$"),
    GREEN_HOUSE_BUILD("^greenhouse\\s+build$"),
    WALK("^walk\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)$"),
    PRINT_MAP("^print\\s+map\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)-s\\s+(?<size>.+)$"),
    HELP_READING_MAP("^help\\s+reading\\s+map$"),
    ENERGY_SHOW("^energy\\s+show$"),
    ENERGY_SET_VALUE("^energy\\s+set\\s+(?<value>\\d+)$"),
    ENERGY_UNLIMITED("^energy\\s+unlimited$"),
    INVENTORY_SHOW("^inventory\\s+show$"),
    INVENTORY_TRASH("^inventory\\s+trash\\s+-i\\s+(?<itemName>.+?)\\s+-n\\s+(?<number>\\d+)$"),
    TOOLS_EQUIP("^tools\\s+equip\\s+(?<toolName>.+)$"),
    TOOLS_SHOW_CURRENT("^tools\\s+show\\s+current$"),
    TOOLS_SHOW_AVAILABLE("^tools\\s+show\\s+available$"),
    TOOLS_UPGRADE("^tools\\s+upgrade\\s+(?<toolName>.+)$"),
    TOOLS_USE_DIRECTION("^tools\\s+use\\s+-d\\s+(?<direction>.+)$"),
    CRAFT_INFO("^craftinfo\\s+-n\\s+(?<craftName>.+)$"),
    PLANT("^plant\\s+-s\\s+(?<seed>.+?)\\s+-d\\s+(?<direction>.+)$"),
    SHOW_PLANT("^showplant\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)$"),
    FERTILIZE("^fertilize\\s+-f\\s+(?<fertilizer>.+?)\\s+-d\\s+(?<direction>.+)$"),
    HOW_MUCH_WATER("^howmuch\\s+water$"),
    CRAFTING_SHOW_RECIPES("^crafting\\s+show\\s+recipes$"),
    CRAFTING_CRAFT("^crafting\\s+craft\\s+(?<itemName>.+)$"),
    PLACE_ITEM("^place\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-d\\s+(?<direction>.+)$"),
    CHEAT_ADD_ITEM("^cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-c\\s+(?<count>\\d+)$"),
    COOKING_REFRIGERATOR_PICK("^cooking\\s+refrigerator\\s+pick\\s+(?<item>.+)$"),
    COOKING_REFRIGERATOR_PUT("^cooking\\s+refrigerator\\s+put\\s+(?<item>.+)$"),
    COOKING_SHOW_RECIPES("^cooking\\s+show\\s+recipes$"),
    EAT("^eat\\s+(?<food_name>.+)$"),
    BUILD("^build\\s+-a\\s+(?<building_name>.+?)\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)$"),
    BUY_ANIMAL("^buy\\s+animal\\s+-a\\s+(?<animal>.+?)\\s+-n\\s+(?<name>.+)$"),
    PET("^pet\\s+-n\\s+(?<name>.+)$"),
    CHEAT_SET_FRIENDSHIP("^cheat\\s+set\\s+friendship\\s+-n\\s+(?<animalName>.+?)\\s+-c\\s+(?<amount>\\d+)$"),
    ANIMALS("^animals$"),
    SHEPHERD("^shepherd\\s+animals\\s+-n\\s+(?<animalName>.+?)\\s+-l\\s+(?<x>\\d+)\\s*,\\s*(?<y>\\d+)$"),
    FEED_HAY("^feed\\s+hay\\s+-n\\s+(?<animalName>.+)$"),
    PRODUCES("^produces$"),
    COLLECT_PRODUCE("^collect\\s+produce\\s+-n\\s+(?<name>.+)$"),
    SELL_ANIMAL("^sell\\s+animal\\s+-n\\s+(?<name>.+)$"),
    FISHING("^fishing\\s+-p\\s+(?<fishingPole>.+)$"),
    ARTISAN_USE("^artisan\\s+use\\s+(?<artisanName>.+?)\\s+(?<item1Name>.+)$"),
    ARTISAN_GET("^artisan\\s+get\\s+(?<artisanName>.+)$"),
    SHOW_ALL_PRODUCTS("^show\\s+all\\s+products$"),
    SHOW_ALL_AVAILABLE_PRODUCTS("^show\\s+all\\s+available\\s+products$"),
    PURCHASE("purchase\\s+(?<productName>.+?)\\s+-n\\s+(?<count>\\d+)"),
    CHEAT_ADD_DOLLARS("^cheat\\s+add\\s+(?<count>\\d+)\\s+dollars$"),
    SELL_PRODUCT("^sell\\s+(?<productName>.+?)\\s+-n\\s+(?<count>\\d+)$"),
    FRIENDSHIPS("^friendships$"),
    TALK("^talk\\s+-u\\s+(?<username>.+?)\\s+-m\\s+(?<message>.+)$"),
    TALK_HISTORY("^talk\\s+history\\s+-u\\s+(?<username>.+)$"),
    GIFT("^gift\\s+-u\\s+(?<username>.+?)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\d+)$"),
    GIFT_LIST("^gift\\s+list$"),
    GIFT_HISTORY("^gift\\s+history\\s+-u\\s+(?<username>.+)$"),
    GIFT_RATE("^gift\\s+rate\\s+-i\\s+(?<giftNumber>\\d+)\\s+-r\\s+(?<rate>\\d+)$"),
    HUG("^hug\\s+-u\\s+(?<username>.+)$"),
    FLOWER("^flower\\s+-u\\s+(?<username>.+)$"),
    ASK_MARRIAGE("^ask\\s+marriage\\s+-u\\s+(?<username>.+?)\\s+-r\\s+(?<ring>.+)$"),
    RESPOND_MARRIAGE_ACCEPT("^respond\\s+–accept\\s+-u\\s+(?<username>.+)$"),
    RESPOND_MARRIAGE_REJECT("^respond\\s+–reject\\s+-u\\s+(?<username>.+)$"),
    START_TRADE("^start\\s+trade$"),
    TRADE("^trade\\s+-u\\s+(?<username>.+?)\\s+-t\\s+(?<type>.+?)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\d+)\\s+[-p\\s+(?<price>\\d+)]\\s+[-ti\\s+(?<targetItem>.+?)\\s+-ta\\s+(?<targetAmount>\\d+)]$"),
    TRADE_LIST("^trade\\s+list$"),
    TRADE_RESPOND_ACCEPT("^trade\\s+response\\s+–accept\\s+-i\\s+(?<id>\\d+)$"),
    TRADE_RESPOND_REJECT("^trade\\s+response\\s+–reject\\s+-i\\s+(?<id>\\d+)$"),
    TRADE_HISTORY("^trade\\s+history$"),
    MEET_NPC("^meet\\s+NPC\\s+(?<npcName>.+)$"),
    GIFT_NPC("^gift\\s+NPC\\s+(?<npcName>.+?)\\s+-i\\s+(?<item>.+)$"),
    FRIENDSHIP_NPC_LIST("^friendship\\s+NPC\\s+list$"),
    QUESTS_LIST("^quests\\s+list$"),
    QUESTS_FINISH("^quests\\s+finish\\s+-i\\s+(?<index>\\d+)$"),
    SHOW_MENU(Command.SHOW_MENU),
    EXIT_MENU(Command.EXIT_MENU),
    ENTER_MENU(Command.ENTER_MENU);

    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    private Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    @Override
    public boolean matches(String input) {
        return getMatcher(input).matches();
    }

    @Override
    public String getGroup(String input, String group) {
        Matcher matcher = getMatcher(input);
        matcher.find();
        return matcher.group(group);
    }
}
