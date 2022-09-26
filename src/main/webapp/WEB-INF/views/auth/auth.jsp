<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../dynamics/css.jspf" %>
<body>
<form method="post" action='<c:url value="/auth"/>'>
    <div class="auth">
        <label>Email</label>
        <input type="text" class="form" name="username" placeholder="username" required>
        <label>Hasło</label>
        <input type="password" class="form" name="password" placeholder="password" required>
        <label class="remember">
            <input name="remember-me" type="checkbox">
            Zapamiętaj mnie
        </label>
        <button class="submit-button" type="submit">Zaloguj</button>
    </div>
</form>
</body>
</html>