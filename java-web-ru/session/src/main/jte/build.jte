@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(
    content = @`
        @if(page.getError() != null)
            <div class="mb-3">
                <p>${page.getError()}</p>
            </div>
        @endif

        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.loginPath()}" method="post">
                <div class="mb-3">
                    <label class="form-label">
                        Логин
                        <input type="text" class="form-control" name="name" value="${page.getName()}" />
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label">Пароль</label>
                    <input type="password" class="form-control" name="password"/>
                </div>
                <input type="submit" class="btn btn-primary" value="Войти" />
            </form>
        </div>
    `
)