<%--
  Created by IntelliJ IDEA.
  User: Skori
  Date: 17.12.2017
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
        <script src="${pageContext.servletContext.contextPath}/web/js/jsScripts.js"></script>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/web/css/styleFireBets.css" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Dosis:400,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Courgette" rel="stylesheet">
    </head>


    <body>

        <div class="top">
            <div class="logo-top">
                FireBets
            </div>

            <div class = "authorization">
                <div class="auth-form">
                    <form>
                        <div class="input-left input-login">
                            <input type="text" name="login" placeholder='Логин' autocomplete="off" value=""/>
                        </div>

                        <div class="input-right input-pass">
                            <input type="password" name="password" class="formlogin" placeholder='Пароль' autocomplete="off" value=""/>
                        </div>

                        <div class="input-left input-enter">
                            <input id="enter" type="submit" value="Вход"/>
                        </div>

                        <div class="remember-pass">
                            <a href="#" class="link-block-remember-pass text-center">Забыли пароль? </a>
                        </div>

                        <div class="input-left input-registr">
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_registration" title="Регистрация в букмекерской конторе Fire Bets" class="link-block-registration">Регистрация </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <header class="text-center">
            <ul class="menu">
                <li><a href="../index.html" class="link-block-menu"><p>Главная</p></a></li>
                <li class="has-dropdown">
                    <a href="#" class="link-block-menu"><p>Профиль</p></a>
                    <ul class="dropdown animated-fast fadeInUpMenu" >
                        <li><a href="#">Мои ставки</a></li>
                        <li><a href="#">Кошелёк</a></li>
                    </ul>
                </li>
                <li><a href="#" class="link-block-menu"><p>Новости</p></a></li>
                <li><a href="#" class="link-block-menu"><p>О компании</p></a></li>
                <li><a href="#" class="link-block-menu"><p>Связаться с нами</p></a></li>
                <li class="has-dropdown">
                    <a href="#" class="link-block-menu"><p>Помощь</p></a>
                    <ul class="dropdown dropdown-1 animated-fast fadeInUpMenu">
                        <li><a href="#">Как сделать ставку?</a></li>
                        <li><a href="#">Как пополнить счёт?</a></li>
                        <li><a href="#">Как вывести деньги?</a></li>
                    </ul>
                </li>
            </ul>
            </div>
        </header>


        <div class="head-title">
            <div class="middle">
                <h1>Регистрация: </h1>
                <span class="regInfoSpan">Все поля формы являются обязательными к заполнению.</span>
            </div>
        </div>


        <div class="registerSteps">

            <form onsubmit="return validateForm()" name="reg-form">
                <div class="label">Логин:</div>
                <div class="field">
                    <input type="text" name="login" class="inputPole" value="" placeholder=" " required />

                </div>

                <div class="label">Имя:</div>
                <div class="field-fname field">
                    <input type="text" name="name" class="inputPole" value="" placeholder=" " pattern="^([A-Z])+([a-z])+$" required /> <span></span>

                </div>

                <div class="label">Фамилия:</div>
                <div class="field field-fname field-sname">
                    <input type="text" name="surname" class="inputPole" value="" placeholder=" " pattern="^([A-Z])+([a-z])+$" required /> <span></span>
                </div>

                <div class="label">Номер телефона:</div>

                <div class="field field-fname field-phone">
                    <input type="text" name="phone" placeholder="+(*) (**) *** ** **" autocomplete="off" value="" pattern="^[\\+][0-9]{11}([0-9]+)?$" class="inputPole" required/> <span></span>
                    <div class="errorMsg" id="phoneError"></div>
                </div>

                <div class="label">E-mail:</div>
                <div class="field field-fname field-email">
                    <input type="email" name="email" class="inputPole" placeholder=" " value="" maxlength="45" pattern=".+@.+\..+" required/> <span></span>
                    <div class="errorMsg" id="emailError"></div>
                </div>


                <div class="">
                    <div class="label">Пароль:</div>
                    <div class="field field-fname field-pass">
                        <input type="password" name="pwd1" class="inputPole" placeholder=" " required pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" id="err-pwd1"> <span></span>
                    </div>
                </div>

                <div class="overflow">
                    <div class="label">Подтвердите пароль:</div>
                    <div class="field">
                        <input type="password" name="pwd2" class="inputPole" > <span class="err" id="err-pwd2"></span>
                    </div>
                </div>


                <div class="but">
                    <input type="hidden" name="command" value="registration"/>
                    <input type="submit" value="Зарегистрироваться">
                </div>
            </form>

        </div>

        <footer>
            <div class="logo-footer"><p>FireBets</p></div>
            <div><p>© Общество с ограниченной ответственностью «FireBets», 2017</p>
                <p>Не зарегистрированный товарный знак FireBets.BY</p></div>
        </footer>
    </body>
</html>
