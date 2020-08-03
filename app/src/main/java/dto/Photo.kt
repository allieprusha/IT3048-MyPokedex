package dto

import com.google.type.DateTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Photo (var localUri : String = "", var remoteUri : String = "", var dateTaken : Date = Date(), var id : String = "") {
}