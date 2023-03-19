package com.musala.deliverydrones;

public interface MessageConstants {
    interface ErrorMessages {
        String BAD_REQUEST_ERROR = "Bad Request Error";
        String INTERNAL_SERVER_ERROR = "Service unavailable for a while please try again later.";
        String NOT_FOUND_ERROR = "Data not found.";
        String WEIGHT_LIMIT_EXCEED = "Medication weight is higher than the capacity of the drone.";
        String INVALID_LOADING_STATE = "Medication can only be loaded when the drone is IDLE.";
        String BATTERY_LOW_FOR_LOADING = "Drone battery low cannot load the medications.";
    }

    interface SuccessMessages {
    }

    interface ValidationMessages {
    }
}
