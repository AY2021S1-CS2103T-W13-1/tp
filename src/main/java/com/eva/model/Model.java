package com.eva.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import com.eva.commons.core.GuiSettings;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Staff> PREDICATE_SHOW_ALL_STAFFS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' persons data file path.
     */
    Path getEvaDatabaseFilePath();

    /**
     * Returns the user prefs' staff data file path.
     */
    Path getStaffDatabaseFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setEvaDatabaseFilePath(Path evaDatabaseFilePath);

    /**
     * Sets the user prefs' address book file path.
     */
    void setStaffDatabaseFilePath(Path staffDatabaseFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setPersonDatabase(ReadOnlyEvaDatabase<Person> personDatabase);

    /** Returns the EvaDatabase */
    ReadOnlyEvaDatabase<Person> getPersonDatabase();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given leave to the given staff.
     * {@code leave} must not already exist in the staff's {@code leaves}.
     */
    void addStaffLeave(Staff target, Leave leave);

    /**
     * Returns true if a staff with the same identity as {@code staff} exists in the address book.
     */
    boolean hasStaffLeave(Staff target, Leave leave);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setStaffDatabase(ReadOnlyEvaDatabase<Staff> personDatabase);

    /** Returns the EvaDatabase */
    ReadOnlyEvaDatabase<Staff> getStaffDatabase();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasStaff(Staff person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteStaff(Staff target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addStaff(Staff person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setStaff(Staff target, Staff editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Staff> getFilteredStaffList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStaffList(Predicate<Staff> predicate);

    void addApplication(Application toAdd);
}
