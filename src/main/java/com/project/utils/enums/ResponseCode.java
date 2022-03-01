package com.project.utils.enums;

public enum ResponseCode {
    OK("200","OK"),
    NOT_FOUND("404", "Entity not found"),
    NULL_VALUE("500", "Parameter not found"),
    CONSTRAINT("501", "Invalid operation. Entity has active relations"),
    CONSTRAINT_NOT_EXIST("501", "Invalid operation. Entity relation does not exist"),
    FINISHED_ALARM("500", "The Alarm is already finished"),
    ENTITY_EXISTS("500", "Entity Already Exist"),
    NO_RESPONSE_TIME_RECORD("500", "Alarm has no recorded response time"),
    DEPARTMENT_CANNOT_BE_DELETED("500", "Department cannot be deleted. Has assigned equipment"),
    ALARM_CATEGORY_CANNOT_BE_DELETED_BY_ALARMS("500","Category Alarm cannot be deleted. Has assigned Alarms"),
    ALARM_CATEGORY_CANNOT_BE_DELETED_BY_LEVELS("500", "Category Alarm cannot be deleted. Has assigned levels"),
    CONSTRAINT_RESPONSE_DATETIME_RECORD("500","Invalid operation. Response date and time cannot be earlier than the creation time"),
    CONSTRAINT_RESOLVE_AND_CREATE_DATETIME_RECORD("500","Invalid operation. Resolution date and time cannot be earlier than the creation time"),
    CONSTRAINT_RESOLVE_AND_RESPONSE_DATETIME_RECORD("500", "Invalid operation. Resolution date and time cannot be earlier than the response time"),
    CONSTRAINT_RESPONSE_RECORD_ATTENDED("500","Invalid operation. Alarm is already attended "),
    CONSTRAINT_DELETE_ALARM_HAS_ACTIVATION_RECORDS("500", "Invalid operation. The alarm has alarm activation records"),
    EQUIPMENT_CANNOT_BE_DELETED_BY_CATEGORY_ALARMS("500","Invalid operation. Equipment has alarm categories"),
    LEVEL_CANNOT_BE_DELETED_BY_USER_GROUPS("500", "Invalid operation. Level is related to user groups")
    ;


    ResponseCode(String code, String message){
        this.code = code;
        this.message = message;
    }
    private String code;
    private String message;

    public String getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }
}
