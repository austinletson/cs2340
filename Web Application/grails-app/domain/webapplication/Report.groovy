package webapplication

class Report {
    Date reportDate
    Integer reportNumber
    String userName
    Double latitude
    Double longitude
    String type
    String condition
    static belongsTo = [user: User]

    static constraints = {
    }
}
