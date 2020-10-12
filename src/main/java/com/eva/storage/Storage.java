package com.eva.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import com.eva.commons.exceptions.DataConversionException;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.ReadOnlyUserPrefs;
import com.eva.model.UserPrefs;
import com.eva.model.person.Person;

/**
 * API of the Storage component
 */
public interface Storage extends EvaStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPersonDatabaseFilePath();

    @Override
    Optional<ReadOnlyEvaDatabase<Person>> readPersonDatabase() throws DataConversionException, IOException;

    @Override
    void savePersonDatabase(ReadOnlyEvaDatabase<Person> addressBook) throws IOException;

}
