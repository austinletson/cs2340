package webapplication

class PurityReport {
    Date reportDate
    Long ReportNumber
    String userName
    Double latitude
    Double longitude
    String condition
    Double virusPPM
    Double contaminantPPM
    User user
    static belongsTo = [user: User]

    static constraints = {
    }
}
