package enumerations;

public enum BatchState {

        InTransit(0), Arrived(1), Storaged(2);

        private final int code;

        BatchState(int code){
            this.code = code;
        }

        public int getCode(){
            return code;
        }

    }

