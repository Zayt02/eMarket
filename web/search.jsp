<%@page import="entity.Product"%>
<%@page import="java.util.Collection"%>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <style>
        #container{
            margin: 50px 0 70px 100px;
        }
        #panes img{
            width: 200px;
            height: 200px;
        }
        #panes div.col-4{
            padding: 30px 0 10px 0;
            border-bottom: 1px solid red;
        }
        #bot img{
            width:150px;
            height: 200px;
        }

    </style>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <script src="js/jquery.tools.min.js"></script>
    <script>
        $(function () {
            $("#prod_nav ul").tabs("#panes > div", {
                effect: 'fade',
                fadeOutSpeed: 400
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            $(".pane-list li").click(function () {
                window.location = $(this).find("a").attr("href");
                return false;
            });
        });
    </script>
    <% Collection<Product> search_result = (Collection<Product>) request.getAttribute("search_result");%>
    <div id="container">
        <h2>Search result</h2>
        <div id="prod_wrapper">
            <div id="panes">
                <c:forEach var="product" items="${search_result}">
                    <div>
                        <img
                            src="${initParam.imgProductPath}${product.getImage()}" alt="No image">
                        <h5>${product.getName()}</h5>
                        <p>${product.getDescriptionDetail()}</p>
                        <p style="text-align:right; margin-right: 16px">
                            <a href="<c:url
                                   value='product?${product.productId}'/>" class="button button8">More Info</a>
                            <a href="<c:url value='addToCart?${product.productId}'/>" class="button">Buy
                               Now</a>
                               </p>
                               </div>
                            </c:forEach>
                            </div>
                            <!-- END tab panes -->
                            <br clear="all">
                            <br clear="all">
                            <!-- navigator -->
                             <div id="prod_nav">
                <ul>
                    <c:set var="countProduct" scope="request" value="${1}" />
                    <c:forEach var="product" items="${search_result}">
                        <li>
                            <a href="#${countProduct}">
                                <img
                                    src="${initParam.imgProductPath}${product.getImage()}" width="75" height="75" alt="">
                                <strong>${product.getName()}</strong> $
                                ${product.getPrice()}
                            </a>
                        </li>
                        <c:set var="countProduct" scope="request"
                               value="${countProduct+1}" />
                    </c:forEach>
                </ul>
            </div>
                            <!-- close navigator -->
                </div>
                <div style="clear:both"></div>
                <div style="clear:both; height: 40px"></div>
            </div>
        </div>
</body>

