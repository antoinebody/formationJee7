package fr.dawan.projet1.ws.validator.custom;

import javax.validation.Payload;

public class Severity {
    public static class Info implements Payload {};
    public static class Error implements Payload {};
}
