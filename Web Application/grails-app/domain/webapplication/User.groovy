package webapplication

import com.sun.org.apache.regexp.internal.RE

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
    }
}
