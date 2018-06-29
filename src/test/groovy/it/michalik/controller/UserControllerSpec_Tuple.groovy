package it.michalik.controller

import org.apache.log4j.Logger
import spock.lang.Specification

class UserControllerSpec_Tuple extends Specification {
    def subject = new UserController()
    def log = Mock(Logger)

    def "fetchUserNameAndHeightByIdAndLogItVavr: when user id is 1"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 1"
            subject.fetchUserNameAndHeightByIdAndLogItVavr(1)
        then: "user name is kamil"
            1 * log.info("User name:Kamil")
        and: "and height 183"
            1 * log.info("User height:183")

    }
}
