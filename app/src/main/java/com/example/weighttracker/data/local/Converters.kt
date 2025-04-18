import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun toEpochDay(date: LocalDate): Long = date.toEpochDay()

    @TypeConverter
    fun toLocalDate(epochDay: Long): LocalDate = LocalDate.ofEpochDay(epochDay)
}