package nasa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import nasa.model.ReadOnlyNasaBook;
import nasa.commons.exceptions.DataConversionException;

/**
 * Represents a storage for {@link nasa.model.NasaBook}.
 */
public interface NasaBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNasaBookFilePath();

    /**
     * Returns NASABook data as a {@link ReadOnlyNasaBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyNasaBook> readNasaBook() throws DataConversionException, IOException, nasa.commons.exceptions.DataConversionException;

    /**
     * @see #getNasaBookFilePath()
     */
    Optional<ReadOnlyNasaBook> readNasaBook(Path filePath) throws DataConversionException, IOException, nasa.commons.exceptions.DataConversionException;

    /**
     * Saves the given {@link ReadOnlyNasaBook} to the storage.
     * @param NasaBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNasaBook(ReadOnlyNasaBook NasaBook) throws IOException;

    /**
     * @see #saveNasaBook(ReadOnlyNasaBook)
     */
    void saveNasaBook(ReadOnlyNasaBook NasaBook, Path filePath) throws IOException;

}
