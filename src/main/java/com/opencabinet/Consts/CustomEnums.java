package com.opencabinet.Consts;

public class CustomEnums {
    public static enum REQ_MODE {
        LOG_IN(0), LOG_IN_SUCCESS(1), LOG_IN_FAIL(2),
        REQ_DRAWER_ITEMS(3), REQ_DRAWER_CORE(4), REQ_ITEM_CORE(5), REQ_ITEM_PACKAGE(5), REQ_VALID_DRAWERS(14),
        ADD_DRAWER(6), ADD_ITEM(7), ADD_DRAWER_FAIL(12), ADD_DRAWER_SUCCESS(13),
        REMOVE_DRAWER(8), REMOVE_ITEM(9),
        RENAME_DRAWER(10), RENAME_ITEM(11);

        private final int value;
        private REQ_MODE(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    };

    public int getReqModeValue(REQ_MODE mode){
        return mode.getValue();
    }

    public static enum LAYOUT {
        DOCO(0), IMG(1), NOTE(2);

        private final int value;
        private LAYOUT(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    };

    public int getLayoutValue(LAYOUT mode){
        return mode.getValue();
    }

    public static enum SEND_MODE {
        FULL(0), POINTER(1);

        private final int value;
        private SEND_MODE(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    };

    public int getSendModeValue(SEND_MODE mode){
        return mode.getValue();
    }


    public static enum PRIVACY {
        PUBLIC(0), PRIVATE(1);

        private final int value;
        private PRIVACY(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    };

    public int getClientActionValue(PRIVACY mode){
        return mode.getValue();
    }
}
