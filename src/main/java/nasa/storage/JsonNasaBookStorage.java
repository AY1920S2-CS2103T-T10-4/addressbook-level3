package nasa.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import nasa.commons.core.LogsCenter;
import nasa.commons.exceptions.DataConversionException;
import nasa.commons.exceptions.IllegalValueException;
import nasa.commons.util.FileUtil;
import nasa.commons.util.JsonUtil;
import nasa.model.ReadOnlyHistory;
import nasa.model.ReadOnlyNasaBook;
import nasa.model.module.UniqueModuleList;

/**
 * A class to access NasaBook data stored as a json file on the hard disk.
 */
public class JsonNasaBookStorage implements NasaBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNasaBookStorage.class);

    private Path filePath;

    public JsonNasaBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNasaBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNasaBook> readNasaBook() throws DataConversionException {
        return readNasaBook(filePath);
    }

    /**
     * Similar to {@link #readNasaBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyNasaBook> readNasaBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableNasaBook> jsonNasaBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableNasaBook.class);
        if (!jsonNasaBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNasaBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveNasaBook(ReadOnlyNasaBook nasaBook) throws IOException {
        saveNasaBook(nasaBook, filePath);
    }

    @Override
    public void saveUltimate(ReadOnlyNasaBook nasaBook, ReadOnlyHistory<UniqueModuleList> historyBook) throws IOException {
        saveUltimate(nasaBook, historyBook, filePath);
    }

    /**
     * Similar to {@link #saveNasaBook(ReadOnlyNasaBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNasaBook(ReadOnlyNasaBook nasaBook, Path filePath) throws IOException {
        requireNonNull(nasaBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNasaBook(nasaBook), filePath);
    }

    public void saveUltimate(ReadOnlyNasaBook nasaBook, ReadOnlyHistory<UniqueModuleList> historyBook, Path filePath) throws IOException {
        requireNonNull(nasaBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNasaBook(nasaBook), filePath);
        JsonUtil.saveJsonFile(new JsonAdaptedHistory(historyBook), filePath);

    }

}

