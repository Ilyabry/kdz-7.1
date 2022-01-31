val service = WallService()

data class Post(
    var id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Long,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val copyright: Copyright?,
    val reposts: Reposts?,
    val views: Views?,
    val postType: String,
    val attachments: Attachments?,
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val postponedId: Long,
)

class Comment(
    val ownerId: Int,
    val postId: Int,
    val fromGroup: Int = 0,
    val message: String,
    val replyToComment: Int,
    val stickerId: Int,
    val guid: String
)

class PostNotFoundException(message:String): RuntimeException(message)

class Copyright {
    val id   = 0
    val link = ""
    val name = ""
    val type = ""
}

class Reposts {
    val count   = 0
    val userReposted = false
}

class Views {
    val count   = 0
}


fun main() {

    val objCopyright = Copyright()
    val objReposts = Reposts()
    val objViews = Views()
    val vId = 0
    val post = Post(id = vId,ownerId = 1,fromId = 1,createdBy = 5,date = 1234231,text = "Текст записи..",
        replyOwnerId = 46,replyPostId = 456,friendsOnly = false,
        copyright = objCopyright,reposts = objReposts,views = null,
        postType = "post",attachments = null,signerId = 1,canPin = true,canDelete = true,canEdit = true,isPinned = false,
        markedAsAds = false,isFavorite = false,postponedId = 0)
    println("Будет создан пост..")
    println(service.add(post))

    val post2 = Post(id = vId,ownerId = 1,fromId = 1,createdBy = 5,date = 1234231,text = "измененный текст записи..",
        replyOwnerId = 46,replyPostId = 456,friendsOnly = false,
        copyright = objCopyright,reposts = objReposts,views = objViews,
        postType = "post",attachments = null,signerId = 1,canPin = true,canDelete = true,canEdit = true,isPinned = false,
        markedAsAds = false,isFavorite = false,postponedId = 0)
    println("Попытка апдейта поста номер $vId")
    println(updPost(post2))

    val vIdNew = 1000
    val post3 = Post(id = vIdNew,ownerId = 10,fromId = 11,createdBy = 51,date = 121231,text = "совсем новый текст ..",
        replyOwnerId = 46,replyPostId = 456,friendsOnly = false,
        copyright = objCopyright,reposts = objReposts,views = objViews,
        postType = "post",attachments = null,signerId = 1,canPin = true,canDelete = true,canEdit = true,isPinned = false,
        markedAsAds = false,isFavorite = false,postponedId = 0)
    println("Попытка апдейта поста номер $vIdNew")
    println(updPost(post3))

    val comment = Comment(ownerId = 1,postId = 0,message = "Коментарий к посту..",replyToComment = 0,stickerId = 0,guid = "")
    service.createComment(comment)
}

fun updPost(post:Post):String{
    val vId = post.id
    return if (service.update(post)) "Пост под номером $vId был успешно изменен"
    else "Не удалось изменить пост под номером $vId"
}