package webapplication

class User {
    String username
    String password
    String type
    String email
    String address
    String title
    String name
    Long id
    static hasMany = [reports: Report, purityReports: PurityReport]

    static constraints = {
        username()
        title()
        name()
        address()
        email(email: true)
        type(inList: ["Manager", "User", "Administrator", "Worker"])
        password()
    }
}
