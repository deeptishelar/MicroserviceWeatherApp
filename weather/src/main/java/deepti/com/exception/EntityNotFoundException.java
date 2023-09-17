package deepti.com.exception;

    public class EntityNotFoundException extends WeatherAppGlobalException {
        public EntityNotFoundException() {
            super("Requested entity not present in the DB.", GlobalErrorCode.ERROR_ENTITY_NOT_FOUND);
        }

        public EntityNotFoundException (String message) {
            super(GlobalErrorCode.ERROR_ENTITY_NOT_FOUND, "Requested entity not present in the DB with given ID:" + message);
        }

}
