interface Attachment {
    val type : String
}
class Attachments {
    var attachments = emptyArray<Attachment>()

    fun add(attachment: Attachment): Attachment{
        attachments += attachment
        return attachments.last()
    }
}

class Photo(
    val id: Int,
    val albumId: Int,
    val userId: Int,
    val text: String
)
class PhotoAttachment(override val type: String="photo",
                      val photo: Photo) : Attachment

class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val date: Int
)
class VideoAttachment(override val type: String="video",
                      val video: Video) : Attachment

class Audio(
    val id: Int,
    val ownerId: Int,
    val userId: Int,
    val albumId: Int
)
class AudioAttachment(override val type: String="audio",
                      val audio: Audio) : Attachment

class Doc(
    val id: Int,
    val ownerId: Int,
    val title: Int,
    val size: String
)
class DocAttachment(override val type: String="doc",
                    val doc: Doc) : Attachment

class Page(
    val id: Int,
    val title: String,
    val views: Int,
    val created: Int,
    val view_url: String
)
class PicturesAttachment(override val type: String="page",
                         val page: Page) : Attachment