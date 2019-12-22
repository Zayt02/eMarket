<%-- 
    Document   : checkout
    Created on : Dec 15, 2019, 12:06:27 AM
    Author     : Zayt
--%>


<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="entity.CustomerAddress"%>
<%@page import="entity.Customer"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
        <link rel="stylesheet" type="text/css" media="screen" href="menu/css/simple_menu.css">
        <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen">
        <!-- JS Files -->
        <script src="js/jquery.min.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/slides/slides.min.jquery.js"></script>
        <script src="js/cycle-slider/cycle.js"></script>
        <script src="js/nivo-slider/jquery.nivo.slider.js"></script>
        <script src="js/tabify/jquery.tabify.js"></script>
        <script src="js/prettyPhoto/jquery.prettyPhoto.js"></script>
        <script src="js/twitter/jquery.tweet.js"></script>
        <script src="js/scrolltop/scrolltopcontrol.js"></script>
        <script src="js/portfolio/filterable.js"></script>
        <script src="js/modernizr/modernizr-2.0.3.js"></script>
        <script src="js/easing/jquery.easing.1.3.js"></script>
        <script src="js/kwicks/jquery.kwicks-1.5.1.pack.js"></script>
        <script src="js/swfobject/swfobject.js"></script>
        <!-- FancyBox -->
        <link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox.css" media="all">
        <script src="js/fancybox/jquery.fancybox-1.2.1.js"></script>
    </head>
    <body>
        <c:set var='view' value='/checkout' scope='session' />
        <script src="js/jquery.validate.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#checkoutForm").validate({
                    rules: {
                        name: "required",
                        email: {required: true,
                            email: true
                        },
                        phone: {
                            required: true,
                            number: true,
                            minlength: 9
                        },
                        address: {
                            required: true}
                        //                        },
                        //                        creditcard: {
                        //                            required: true,
                        //                            creditcard: true
                        //                        }
                    }
                });
            });
        </script>
        <div id="container">
            <!--        <div class="one-half">
                        <div class="heading_bg">
                            <h2>Tabs</h2>
                        </div>-->
            <!--    <ul id="tabify_menu" class="menu_tab" style="margin: 0;">
                  <li class="active"><a href="#fane1">Profile</a></li>
                  <li><a href="#fane2">Change Password</a></li>
                </ul>-->
            <!--            <div id="fane1" class="tab_content">
            
                        </div>
                        <div id="fane2" class="tab_content">
                            <h3>Tab 2</h3>
                            <p>Content 2</p>
                        </div>-->
            <div class="two-third">
                <div class="heading_bg">
                    <h2>Profile</h2>
                    <ul id="tabify_menu" class="menu_tab" style="margin: 0;">
                        <li class="active"><a href="#fane1">Profile</a></li>
                        <li><a href="#fane2">Change Password</a></li>
                        <li><a href="#fane3">Update Address</a></li>
                    </ul>

                </div>
                <!--                <p>
                                    <strong>In order to purchase the items in your shopping cart,
                                        please provide us with the following information:</strong>
                                </p>-->
                <div id="fane1" class="tab_content">
                    <form id="editProfile" action="editProfile"
                          method="post">                    
                        <fieldset>
                            <label>Login name: ${user.getUserName()}</label>
                        </fieldset>
                        <fieldset>
                            <label>Name<span class="required">*</span></label>
                            <input type="text" name="name" id="email"
                                   value="${user.getName()}" />
                        </fieldset>
                        <fieldset>
                            <label>Email<span class="required">*</span></label>
                            <input type="text" name="email" id="email"
                                   value="${user.getEmail()}" />
                        </fieldset>
                        <fieldset>
                            <label>Phone <span class="required">*</span></label>
                            <input type="text" name="phone" id="phone"
                                   value="${user.getPhone()}" />
                        </fieldset>
                        <!--            <fieldset>
                                        <label>Address <span class="required">*</span></label>
                                        <input type="text" size="45" name="address" id="address"
                                               value="${param.address}" />
                                    </fieldset>-->
                        <fieldset>
                            <label>City <span class="required">*</span></label>
                            <input type="text" size="45" name="cityRegion"
                                   id="cityRegion" value="${user.getCityRegion()}" />
                        </fieldset>
                        <fieldset>
                            <label>Credit Card Number<span
                                    class="required">*</span></label>
                            <input type="text" size="45" name="creditcard"
                                   id="creditcard" value="${user.getCcNumber()}" />
                        </fieldset>
                        <fieldset>
                            <input value="Save Changes" class="button white"
                                   type="submit">
                        </fieldset>
                    </form>
                </div>
                <div id="fane2" class="tab_content">    
                    <% Integer not_equal = (Integer) request.getAttribute("not_equal");
                        if (not_equal != null && not_equal == 1) {%> 
                    <font color="red"> Incorrect password!</font>
                    <% } else if (not_equal != null && not_equal == 0) {%>
                    <font color="blue"> Successful!</font><% }%>
                    <form id="changePassword" action="changePassword"
                          method="post">
                        <fieldset>
                            <label>Old password<span
                                    class="required">*</span></label>
                            <input type="password" name="old_password"
                                   id="old_password"/>
                        </fieldset>
                        <fieldset>
                            <label>New password<span
                                    class="required">*</span></label>
                            <input type="password" name="new_password"
                                   id="new_password"/>
                        </fieldset>
                        <fieldset>
                            <label>Confirm new password<span
                                    class="required">*</span></label>
                            <input type="password" name="confirm_password"
                                   id="confirm_password"/>
                        </fieldset>
                        <fieldset>
                            <input value="Save Changes" class="button white"
                                   type="submit">
                        </fieldset>
                    </form>
                </div>
                <div id="fane3" class="tab_content">
                    <form id="updateAddress" action="updateAddress"
                          method="post">
                        <% List<CustomerAddress> addresses
                                    = (List<CustomerAddress>) request.getSession().getAttribute("addresses");%>
                        <table border="0">
                            <th>Address</th>
                            <th></th>
                                <c:forEach var="address" items="${addresses}" varStatus="iter">
                                <tr>
                                    <td>${address.getAddress()}</td>
                                    <td><a href="editAddress?${iter.index}">Edit</a></td>
                                </tr>
                            </c:forEach>
                        </table>   
                        <a href="newAddress.jsp">New Address</a>
                    </form>
                </div>
                <div style="clear:both; height: 40px"></div>
            </div>
    </body>
</html>
