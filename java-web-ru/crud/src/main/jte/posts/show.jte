@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage

@param PostPage page

@template.layout.page(
    content = @`
    <div class="px-4 py-5 my-5 text-center">
        <h1 class="display-5 fw-bold">${page.getPost().getName()}</h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">${page.getPost().getBody()}</p>
            <div class="d-grid gap-2 justify-content-sm-center">
                <button type="button" class="btn btn-primary btn-lg px-4 gap-3">Previous</button>
                <button type="button" class="btn btn-primary btn-lg px-4 gap-3">Next</button>
            </div>
        </div>
    </div>
    `
)