class WallService {

    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    fun  add(post : Post): Post {
        posts += post.copy(id = if(posts.isEmpty()) 0 else posts.last().id + 1)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        val vId = post.id
        for ((index, vpost) in posts.withIndex()){
            if (vpost.id == vId) {
                posts[index] = post.copy(ownerId = posts[index].ownerId,date = posts[index].date)
                return true
            }
        }
        return false
    }

    fun createComment(comment: Comment) {
        for ((index, vpost) in posts.withIndex()){
            if (vpost.id == comment.postId) {
                comments += comment
                return
            }
        }
        throw PostNotFoundException("No post with id = ${comment.postId}")
    }

}