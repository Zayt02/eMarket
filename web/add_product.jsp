<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Add product</title>

        <!-- CSS -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/screen.css" />
        <!--[if lte IE 8]>
        <link rel="stylesheet" type="text/css" media="screen" href="css/ie.css" />
        <![endif]-->

        <!-- JS -->
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <!--<script type="text/javascript" src="js/jquery.validate.pack.js"></script>-->
        <script type="text/javascript" src="js/init.js"></script>
        <script>
//            function validateForm() {
//                    var x = document.forms["input"]["name"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    var x = document.forms["input"]["image"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    var x = document.forms["input"]["price"].value;
//                    if (x == "" || x == null ) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    if (x == "" || x == null || typeof(praseFloat(document.forms["input"]["price"].value)) !== number )
//                    {
//                        alert("Price must be a number!");
//                        return false;
//                    }
//                    var x = document.forms["input"]["decription"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    var x = document.forms["input"]["day"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    var x = document.forms["input"]["month"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    var x = document.forms["input"]["year"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    var x = document.forms["input"]["category"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    
//                    var x = document.forms["input"]["decription"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                    var x = document.forms["input"]["description_detail"].value;
//                    if (x == "" || x == null) {
//                        alert("All fields must be filled out");
//                        return false;
//                    }
//                }
//            }
        </script>
    </head>
    <body id="edit-profile">
        <!-- Container -->	
        <div id="container"><div id="container-inner">

                <h1>New Product</h1>
                <form name="input" action="ProductServlet" method="post" >
                    <fieldset>
                        <!-- Your profile photo -->
                        <!--                        <div>
                                                    <label for="photo">Your profile photo</label>
                                                    <input type="file" name="photo" id="photo" />
                                                </div>-->
                        <% Integer is_not_exist = (Integer) request.getAttribute("is_not_exist");
                            if (is_not_exist != null && is_not_exist == 0) {%> <font color="red"> Error: Category id does not exist!</font> 
                        <%} else if (is_not_exist != null && is_not_exist == 1) {%> <font color="blue"> Successful!</font> <%}%>
                        <div>
                            <label for="name_first">Photo directory: </label>
                            <input type="text" name="image" id="name_first" />
                        </div>
                        <!-- Your first name -->
                        <!--                        <div>
                                                    <label for="name_first">Product Id: </label>
                                                    <input type="text" name="product_id" id="name_first" />
                                                </div>-->

                        <!-- Your last name -->
                        <div>
                            <label for="name_last">Name: </label>
                            <input type="text" name="name" id="name_last" />
                        </div>

                        <div>
                            <label for="name_first">Price: </label>
                            <input type="text" name="price" id="name_first" />
                        </div>

                        <!-- About you -->
                        <div>
                            <label for="about_you">Description: </label>
                            <textarea name="description" id="about_you" rows="10" cols="5"></textarea>
                        </div>

                        <fieldset id="section-dob" class="group">
                            <legend><span>Last Update: </span></legend>
                            <!-- Month -->
                            <div>
                                <label for="dob_month">Month</label>
                                <select name="month" id="dob_month">
                                    <option value=""> - Month - </option>
                                    <option value="1">January</option>
                                    <option value="2">February</option>
                                    <option value="3">March</option>
                                    <option value="4">April</option>
                                    <option value="5">May</option>
                                    <option value="6">June</option>
                                    <option value="7">July</option>
                                    <option value="8">August</option>
                                    <option value="9">September</option>
                                    <option value="10">October</option>
                                    <option value="11">November</option>
                                    <option value="12">December</option>
                                </select>
                            </div>

                            <!-- Day -->
                            <div>
                                <label for="dob_day">Day</label>
                                <select name="day" id="dob_day">
                                    <option value=""> - Day - </option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                    <option value="31">31</option>
                                </select>
                            </div>

                            <!-- Year -->
                            <div>
                                <label for="dob_year">Year</label>
                                <select name="year" id="dob_year">
                                    <option value=""> - Year - </option>
                                    <option value="2004">2004</option>
                                    <option value="2003">2003</option>
                                    <option value="2002">2002</option>
                                    <option value="2001">2001</option>
                                    <option value="2000">2000</option>
                                    <option value="1999">1999</option>
                                    <option value="1998">1998</option>
                                    <option value="1997">1997</option>
                                    <option value="1996">1996</option>
                                    <option value="1995">1995</option>
                                    <option value="1994">1994</option>
                                    <option value="1993">1993</option>
                                    <option value="1992">1992</option>
                                    <option value="1991">1991</option>
                                    <option value="1990">1990</option>
                                    <option value="1989">1989</option>
                                    <option value="1988">1988</option>
                                    <option value="1987">1987</option>
                                    <option value="1986">1986</option>
                                    <option value="1985">1985</option>
                                    <option value="1984">1984</option>
                                    <option value="1983">1983</option>
                                    <option value="1982">1982</option>
                                    <option value="1981">1981</option>

                                </select>
                            </div>
                        </fieldset>

                        <div>
                            <label for="dob_year">Category</label>
                            <select name="category_id" id="dob_year">
                                <option value=""> - Category - </option>
                                <option value="1">Mac</option>
                                <option value="2">Ipad</option>
                                <option value="3">Iphone</option>
                                <option value="4">Accessories</option>
                            </select>
                        </div>
                        <!--                            <label for="name_first">Category Id: </label>
                                                    <input type="text" name="category_id" id="name_first" />
                                                </div>-->

                        <div>
                            <label for="about_you">Description Details: </label>
                            <textarea name="description_detail" id="about_you" rows="10" cols="5"></textarea>
                        </div>
                        <div>
                            <label for="name_first">Guaranty </label>
                            <input type="text" name="guaranty" id="name_first" />
                        </div>
                        <div>
                            <label for="name_first">Accessories: </label>
                            <input type="text" name="accessories" id="name_first" />
                        </div>

                        <!-- Controls -->
                        <div class="controls">
                            <input id="submit" name="submit" type="submit" value="Save" />
                            <input id="preview" name="submit" type="submit" value="Preview Profile" />
                        </div>
                    </fieldset>
                </form>

            </div></div>	<!-- /Container -->

    </body>
</html>
