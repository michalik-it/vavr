package it.michalik.controller

import org.apache.log4j.Logger
import spock.lang.Specification

class UserControllerSpec_Try extends Specification {
    def subject = new UserController()
    def log = Mock(Logger)

    def "searchUserByIdJava_1: when user id is -1 then return null"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 1"
            def user = subject.searchUserByIdJava_1(-1)
        then: "user name is null"
            user == null
    }

    def "searchUserByIdVavr_1: when user id is -1 then return null"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 1"
            def user = subject.searchUserByIdVavr_1(-1)
        then: "user name is null"
            user == null
    }

    def "searchUserByIdJava_2: when user id is -1 then return null and log warn"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 1"
            def user = subject.searchUserByIdJava_2(-1)
        then: "user name is null"
            user == null
        and: "log it"
            1 * log.warn(_)
    }

    def "searchUserByIdVavr_2: when user id is -1 then return null and log warn"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 1"
            def user = subject.searchUserByIdVavr_2(-1)
        then: "user name is null"
            user == null
        and: "log it"
            1 * log.warn(_)
    }

    def "searchUserByIdJava_3: when user id is -1 then return log error and throw RuntimeException"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 1"
            subject.searchUserByIdJava_3(-1)
        then: "log it"
            1 * log.error(_)
        and: "throw runtime"
            thrown(RuntimeException)
    }

    def "searchUserByIdVavr_3: when user id is -1 then return log error and throw RuntimeException"() {
        given:
            subject.log = log
        when: "fetch user name with id eq. 1"
            subject.searchUserByIdVavr_3(-1)
        then: "log it"
            1 * log.error(_)
        and: "throw runtime"
            thrown(RuntimeException)
    }
}
