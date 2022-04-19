package parameter;

import java.util.HashMap;

public class ReasonPhrase {
    public static HashMap<String, String> REASON_PHRASE_MAP;

    static {
        REASON_PHRASE_MAP = new HashMap<>();
        REASON_PHRASE_MAP.put("100", "Continue");
        REASON_PHRASE_MAP.put("101", "SwitchingProtocols");
        REASON_PHRASE_MAP.put("200", "OK");
        REASON_PHRASE_MAP.put("201", "Created");
        REASON_PHRASE_MAP.put("202", "Accepted");
        REASON_PHRASE_MAP.put("203", "Non-AuthoritativeInformation");
        REASON_PHRASE_MAP.put("204", "NoContent");
        REASON_PHRASE_MAP.put("205", "ResetContent");
        REASON_PHRASE_MAP.put("206", "PartialContent");
        REASON_PHRASE_MAP.put("300", "MultipleChoices");
        REASON_PHRASE_MAP.put("301", "MovedPermanently");
        REASON_PHRASE_MAP.put("302", "Found");
        REASON_PHRASE_MAP.put("303", "SeeOther");
        REASON_PHRASE_MAP.put("304", "NotModified");
        REASON_PHRASE_MAP.put("305", "UseProxy");
        REASON_PHRASE_MAP.put("307", "TemporaryRedirect");
        REASON_PHRASE_MAP.put("400", "BadRequest");
        REASON_PHRASE_MAP.put("401", "Unauthorized");
        REASON_PHRASE_MAP.put("402", "PaymentRequired");
        REASON_PHRASE_MAP.put("403", "Forbidden");
        REASON_PHRASE_MAP.put("404", "NotFound");
        REASON_PHRASE_MAP.put("405", "MethodNotAllowed");
        REASON_PHRASE_MAP.put("406", "NotAcceptable");
        REASON_PHRASE_MAP.put("407", "ProxyAuthenticationRequired");
        REASON_PHRASE_MAP.put("408", "RequestTime-out");
        REASON_PHRASE_MAP.put("409", "Conflict");
        REASON_PHRASE_MAP.put("410", "Gone");
        REASON_PHRASE_MAP.put("411", "LengthRequired");
        REASON_PHRASE_MAP.put("412", "PreconditionFailed");
        REASON_PHRASE_MAP.put("413", "RequestEntityTooLarge");
        REASON_PHRASE_MAP.put("414", "Request-URITooLarge");
        REASON_PHRASE_MAP.put("415", "UnsupportedMediaType");
        REASON_PHRASE_MAP.put("416", "Requestedrangenotsatisfiable");
        REASON_PHRASE_MAP.put("417", "ExpectationFailed");
        REASON_PHRASE_MAP.put("500", "InternalServerError");
        REASON_PHRASE_MAP.put("501", "NotImplemented");
        REASON_PHRASE_MAP.put("502", "BadGateway");
        REASON_PHRASE_MAP.put("503", "ServiceUnavailable");
        REASON_PHRASE_MAP.put("504", "GatewayTime-out");
        REASON_PHRASE_MAP.put("505", "HTTPVersionnotsupported");
    }
}
