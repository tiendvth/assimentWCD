<%--
  Created by IntelliJ IDEA.
  User: tiendangvan
  Date: 13/06/2022
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside>
    <div id="sidebar" class="nav-collapse">
        <!-- sidebar menu start-->
        <div class="leftside-navigation">
            <ul class="sidebar-menu" id="nav-accordion">
                <li>
                    <a href="login.html">
                        <i class="fa fa-user"></i>
                        <span>Login Page</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/foods/create">
                        <i class="fa fa-user"></i>
                        <span>Category</span>
                    </a>
                </li>
                <li>
                    <a href="admin/categories/create">
                        <i class="fa fa-user"></i>
                        <span>Food</span>
                    </a>
                </li>
                <li>
                    <a href="admin/categories/create">
                        <i class="fa fa-user"></i>
                        <span>User</span>
                    </a>
                </li>

            </ul>
        </div>
        <!-- sidebar menu end-->
    </div>
</aside>
