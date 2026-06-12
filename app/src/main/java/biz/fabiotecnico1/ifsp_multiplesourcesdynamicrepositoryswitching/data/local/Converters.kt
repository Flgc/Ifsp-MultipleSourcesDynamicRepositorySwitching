package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.local

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDate(date: Date): Long = date.time

    @TypeConverter
    fun toDate(timestamp: Long): Date = Date(timestamp)
}