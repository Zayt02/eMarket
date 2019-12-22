<%-- 
    Document   : checkout
    Created on : Dec 15, 2019, 12:06:27 AM
    Author     : Zayt
--%>


<%@page import="entity.CustomerOrder"%>
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
        <div class="heading_bg">
            <h2>Edit Order</h2>
        </div>
        <% CustomerOrder editedOrder = (CustomerOrder) request.getAttribute("editedOrder");%>
        
        <table border="0">
            <th>Confirmation Number</th>
            <th>Customer Username</th>
            <th>Date Created</th>
            <th>Status</th>
            <th></th>

            <tr>
                <td><a href="viewOrder?${editedOrder.getOrderId()}">${editedOrder.getConfirmationNumber()}</a></td>
                <td>${editedOrder.getCustomerId().getUserName()} </td>
                <td>${editedOrder.getDateCreated()}</td>
                <td><form action="editOrder"  method="post">
                        <fieldset>
                            <select name="newStatus">
                                        <option value=""> - Choose Status - </option>
                                        <option value="Processing">Processing</option>
                                        <option value="Packaging">Packaging</option>
                                        <option value="Transporting">Transporting</option>
                                        <option value="Transported">Transported</option>
                                    </select>
                            <fieldset>
                                <input value="Save Change" class="button white"
                                       type="submit" >
                            </fieldset>  
                    </form></td>
            </tr>

        </table>   
    </div>
    <div style="clear:both; height: 40px"></div>
</body>
</html>
