import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {
    val objCopyright = Copyright()
    val objReposts = Reposts()
    val objViews = Views()

    val post = Post(
        id = 0,ownerId = 1,fromId = 1,createdBy = 5,date = 1234231,text = "Текст",
        replyOwnerId = 46,replyPostId = 456,friendsOnly = false,
        copyright = objCopyright,reposts = objReposts,views = null,
        postType = "post",attachments = null,signerId = 1,canPin = true,canDelete = true,canEdit = true,isPinned = false,
        markedAsAds = false,isFavorite = false,postponedId = 0
    )
    val comment = Comment(ownerId = 1,postId = 2,message = "Коментарий",replyToComment = 0,stickerId = 0,guid = "")

    @Test
    fun addTest() {
        val service = WallService()
        service.add(post.copy(text = "Второй текст"))
        service.add(post.copy(text = "Третий текст",views = Views()))
        val testPost = post.copy(text = "Проверка")
        val result = service.add(testPost)
        assertEquals(testPost.copy(id=2), result)
    }

    @Test
    fun updateExistingTest() {
        val service = WallService()
        service.add(post)
        service.add(post.copy(text = "Второй текст"))
        service.add(post.copy(text = "Третий текст",reposts = objReposts))
        val update = post.copy(id = 2,text = "Третий текст",copyright = objCopyright)
        val result = service.update(update)
        assertTrue(result)
    }
    @Test
    fun updateNotExistingTest() {
        val service = WallService()
        service.add(post)
        service.add(post.copy(text = "Второй текст"))
        service.add(post.copy(text = "Третий текст"))
        val update = post.copy(id = 100,text = "Замена текста")
        val result = service.update(update)
        assertFalse(result)
    }

    @Test
    fun updateCommentTest() {
        val service = WallService()
        service.add(post)
        service.add(post.copy(text = "Второй текст"))
        service.add(post.copy(text = "Третий текст"))
        val result = try {
            service.createComment(comment)
            true
        } catch (e:PostNotFoundException){
            false
        }
        assertEquals(true, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrowTest() {
        val service = WallService()
        service.add(post)
        service.add(post.copy(text = "Второй текст"))
        service.createComment(comment)

    }
}