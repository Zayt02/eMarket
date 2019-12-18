<%-- 
    Document   : category
    Created on : Nov 24, 2019, 10:02:35 AM
    Author     : Zayt
--%>
<head>
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
<%@page import="entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="entity.Product"%>
<%
    session.setAttribute("view", "/category");
    Category category = (Category) session.getAttribute("selectedCategory");
%>
<div id="container">
    <div class="one">
        <div class="heading_bg">
            <h2><%=category.getName()%></h2>
        </div>
        <div id="portfolio">
            <div class="portfolio-container" id="columns">
                <ul>
                    <%
                        List<Product> categoryProducts = (List<Product>) session.getAttribute("categoryProducts");
                        for (Product p : categoryProducts) {
                    %>
                    <li class="one-third">
                        <p>
                            <a title="<%=p.getName()%>"
                               href="${initParam.imgProductPath}<%=p.getImage()%>" class="portfolio-item-preview" >
                                <img src="${initParam.imgProductPath}<%=p.getImage()%>" alt=""
                                     width="210" height="145" class="portfolio-img pretty-box">
                            </a>
                        </p>
                        <h4><a href="#"><%=p.getName()%></a></h4>
                        <p><%=p.getDescription()%></p>
                        <p style="text-align: left">
                            <a href="product?<%=p.getProductId()%>"
                               class="button_small white">See Details &raquo;</a>
                        </p>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
    <div style="clear:both; height: 40px"></div>
</div>
