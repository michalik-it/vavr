package it.michalik.controller

import org.apache.log4j.Logger
import spock.lang.Specification

class UserControllerSpec_Option extends Specification {
    def subject = new UserController()
    def log = Mock(Logger)

    //JAVA
    def "fetchUserNameByIdJava when id eq. 1"() {
        when: "fetch user name with id eq. 1"
            def userName = subject.fetchUserNameByIdJava(1)
        then: "user name is kamil"
            userName == "Kamil"
    }

    def "fetchUserNameByIdJava when id eq. 10"() {
        when: "fetch user name with id eq. 10"
            def userName = subject.fetchUserNameByIdJava(10)
        then: "user name is kamil"
            userName == "Error: User does not exist"
    }

    //JAVA8
    def "fetchUserNameByIdJava8 when id eq. 1"() {
        when: "fetch user name with id eq. 1"
        def userName = subject.fetchUserNameByIdJava8(1)
        then: "user name is kamil"
        userName == "Kamil"
    }

    def "fetchUserNameByIdJava8 when id eq. 10"() {
        when: "fetch user name with id eq. 10"
            def userName = subject.fetchUserNameByIdJava8(10)
        then: "user name is kamil"
            userName == "Error: User does not exist"
    }

    def "fetchUserNameByIdAndLogItJava8 when id eq. 10"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 10"
            subject.fetchUserNameByIdAndLogItJava8(10)
        then: "user name is kamil"
            1 * log.info("Error: User does not exist")
    }

    //VAVR
    def "fetchUserNameByIdVavr when id eq. 1"() {
        when: "fetch user name with id eq. 1"
            def userName = subject.fetchUserNameByIdVavr(1)
        then: "user name is kamil"
            userName == "Kamil"
    }

    def "fetchUserNameByIdVavr when id eq. 10"() {
        when: "fetch user name with id eq. 10"
            def userName = subject.fetchUserNameByIdVavr(10)
        then: "user name is kamil"
            userName == "Error: User does not exist"
    }

    def "fetchUserNameByIdAndLogItVavr when id eq. 10"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 10"
            subject.fetchUserNameByIdAndLogItVavr(10)
        then: "user name is kamil"
            1 * log.info("Error: User does not exist")
    }
}
