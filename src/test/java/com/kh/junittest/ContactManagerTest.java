package com.kh.junittest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactManagerTest {
    ContactManager manager;
    @BeforeAll
    public void setupAll(){
        System.out.println("Should print before all tests");
    }

    @BeforeEach
    public void setUp(){
        manager = new ContactManager();
    }
    @Test
    public void shouldCreateContact(){
         manager.addContact("John","Doe","0123456789");
         assertFalse(manager.getAllContacts().isEmpty());
         assertEquals(1,manager.getAllContacts().size());
         assertTrue(manager.getAllContacts().stream()
                 .anyMatch(contact -> contact.getFirstName().equals("John") &&
                         contact.getLastName().equals("Doe")&&
                         contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Should not create Contact when First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class,()->{
            manager.addContact(null, "Doe","0123456789");
        });
    }

    @Test
    @DisplayName("Should not create Contact when Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class,()->{
            manager.addContact("John", null,"0123456789");
        });
    }

    @Test
    @DisplayName("Should not create Contact when Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull(){
        Assertions.assertThrows(RuntimeException.class,()->{
            manager.addContact("John", "Doe",null);
        });
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should Execute After Each Test");
    }
    @AfterAll
    public void tearDownAll(){
        System.out.println("Should be executed at the end of the Test");
    }

    @Test
    @DisplayName("Should Create Contact Only on MAC OS")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on MAC OS")
    public void shouldCreateContactOnlyOnMac(){
        manager.addContact("John","Doe","0123456789");
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
        assertTrue(manager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe")&&
                        contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Should Create Contact Only on MAC OS")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Enabled only on MAC OS")
    public void shouldNotCreateContactOnlyOnWindows(){
        manager.addContact("John","Doe","0123456789");
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
        assertTrue(manager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe")&&
                        contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDEV(){
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        manager.addContact("John","Doe","0123456789");
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
    }
    @RepeatedTest(value = 5,name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetition}")
    @DisplayName("Repeat Contact Creation Test 5 times")
    public void shouldTestContactCreationRepeatedly(){
        manager.addContact("John","Doe","0123456789");
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
    }
    @DisplayName("Value Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789","0234567890","0345678912"})
    public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
        manager.addContact("John","Doe",phoneNumber);
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
    }

    @DisplayName("Method Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber){
        manager.addContact("John","Doe",phoneNumber);
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789","0234567890","0345678912");
    }

    @DisplayName("CSV Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvSource({"0123456789","0234567890","0345678912"})
    public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
        manager.addContact("John","Doe",phoneNumber);
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
    }

    @DisplayName("CSV Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestPhoneNumberFormatUsingCSVFileSource(String phoneNumber) {
        manager.addContact("John","Doe",phoneNumber);
        assertFalse(manager.getAllContacts().isEmpty());
        assertEquals(1,manager.getAllContacts().size());
        
    }

}

