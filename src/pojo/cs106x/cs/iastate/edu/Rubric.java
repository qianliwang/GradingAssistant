package pojo.cs106x.cs.iastate.edu;

public class Rubric {

	private ChapterRubric cr;

	public Rubric(){
		this.cr = new ChapterRubric();
	}

	public ChapterRubric getChapterRubric(String chapterName){

		int week = Integer.valueOf(chapterName);

		FileRubric fr;

		switch(week){

		case 1:
			break;
		case 2:
			fr = new FileRubric("index.html");

			fr.addUnitRubric(".*<!DOCTYPE html>.*", 2,"<!DOCTYPE html>");
			fr.addUnitRubric("<meta(\\s+)charset(\\s*)=(\\s*)\"(\\s*)utf-8(\\s*)\"(\\s*)>", 2,"<meta charset=\"utf-8\" >");
			fr.addUnitRubric("<header>", 1,"<header> is missing");
			fr.addUnitRubric("<h1>", 1,"<h1> is missing");
			fr.addUnitRubric("</h1>", 1,"</h1> is missing");
			fr.addUnitRubric("<nav>", 1,"<nav> is missing");
			fr.addUnitRubric("</nav>", 1,"</nav> is missing");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)index", 2," <a href=\"index.html\"> Home</a>");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)menu", 2," <a href=\"menu.html\"> Menu</a>");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)music", 2," <a href=\"music.html\"> Music</a>");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)jobs", 2," <a href=\"jobs.html\"> Jobs</a>");
			fr.addUnitRubric("&nbsp;", 1,"&nbsp; is not correct");
			fr.addUnitRubric("<ul>", 1,"<ul> is missing");
			fr.addUnitRubric("<li>(\\s*)(\\w.*)</li>", 2,"<li></li> is missing");
			fr.addUnitRubric("</ul>", 1,"</ul> is missing");
			fr.addUnitRubric("<i>", 1,"<i> is missing");
			fr.addUnitRubric("</i>", 1,"</i> is missing");
			fr.addUnitRubric("<\\s*br\\s*>", 1,"<br> is missing");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)mailto", 1, "<a href=\"mailto:yourfirstname@yourlastname.com\">");
			fr.addUnitRubric("&copy;", 1,"&copy; is not correct");


			cr.addFileRubric(fr);

			fr = new FileRubric("menu.html");
			fr.addUnitRubric(".*<!DOCTYPE html>.*", 2,"<!DOCTYPE html>");
			fr.addUnitRubric("<meta(\\s+)charset(\\s*)=(\\s*)\"(\\s*)utf-8(\\s*)\"(\\s*)>", 2,"<meta charset=\"utf-8\">");
			fr.addUnitRubric("<header>", 1,"<header> is missing");
		//	fr.addUnitRubric("</header>", 1,"</header>");
			fr.addUnitRubric("<h1>", 1,"<h1> is missing");
			fr.addUnitRubric("</h1>", 1,"</h1> is missing");
			fr.addUnitRubric("<nav>", 1,"<nav> is missing");
			fr.addUnitRubric("</nav>", 1,"</nav> is missing");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)index", 2," <a href=\"index.html\"> Home</a>");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)menu", 2," <a href=\"menu.html\"> Menu</a>");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)music", 2," <a href=\"music.html\"> Music</a>");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)jobs", 2," <a href=\"jobs.html\"> Jobs</a>");
			fr.addUnitRubric("&nbsp;", 1,"&nbsp; is not correct");
			fr.addUnitRubric("<dl>", 1,"<dl> is missing");
			fr.addUnitRubric("</dl>", 1,"</dl> is missing");
			fr.addUnitRubric("<dt>", 1,"<dt> is missing");
			fr.addUnitRubric("</dt>", 1,"</dt> is missing");
			fr.addUnitRubric("<strong>", 1,"<strong> is missing");
			fr.addUnitRubric("</strong>", 1,"</strong> is missing");
			fr.addUnitRubric("&copy;", 1,"&copy; is not correct");
			fr.addUnitRubric("<a(\\s*)href(\\s*)=(\\s*)\"(\\s*)mailto", 1, "<a href=\"mailto:yourfirstname@yourlastname.com\">");
			fr.addUnitRubric("<i>", 1,"<i> is missing");
			fr.addUnitRubric("</i>", 1,"</i> is missing");
			
			cr.addFileRubric(fr);

			break;
		case 3:
			fr = new FileRubric("index.html");

			fr.addUnitRubric("(\\s*)href(\\s*)=(\\s*)\"javajam", 1, "<link rel=\"stylesheet\" href=\"javajam.css\" /> is incorrect");
			fr.addUnitRubric("<div(\\s+)id(\\s*)=(\\s*)\"wrapper", 1, "<div id=\"wrapper\"> is missing");
			
			cr.addFileRubric(fr);;

			fr = new FileRubric("javajam.css");
			
			fr.addUnitRubric("(\\s*)body(\\s*)\\{(\\s*)background-color(\\s*):([^;]+);(\\s*)color(\\s*):([^;]+);(\\s*)font-family(\\s*):([^;]+)",2," body selector is incorrect, it should be, body {background-color:#ffffcc;color:#330000;font-family:Verdana, Arial, sans-serif;}");
			fr.addUnitRubric("(\\s*)header(\\s*)\\{(\\s*)background-color(\\s*):([^;]+);(\\s*)color(\\s*):([^;]+);(\\s*)text-align(\\s*):([^;]+)",2," header selector is incorrect, it should be, header {background-color:#ccaa66;color:#000000;text-align:center;}");
			fr.addUnitRubric("(\\s*)h1(\\s*)\\{(\\s*)line-height",2,"h1 selector is incorrect, it should be, h1 {line-height: 200%;}");
			fr.addUnitRubric("(\\s*)nav(\\s*)\\{(\\s*)text-align",2,"nav selector is incorrect, it should be, nav {text-align: center;}");
			fr.addUnitRubric("(\\s*)footer(\\s*)\\{(\\s*)background-color(\\s*):([^;]+);(\\s*)color(\\s*):([^;]+);(\\s*)font-size(\\s*):([^;]+);(\\s*)font-style(\\s*):([^;]+);(\\s*)text-align(\\s*):([^;]+)",2," footer selector is incorrect, it should be, footer {background-color:#ccaa66;color:#000000;font-size: .60 em;font-style:italic;text-align:center;}");
			fr.addUnitRubric("(\\s*)#(\\s*)wrapper(\\s*)\\{(\\s*)width(\\s*):([^;]+);(\\s*)margin-right(\\s*):([^;]+);(\\s*)margin-left(\\s*):([^;]+)",2,"#wrapper selector is incorrect, it should be, #wrapper {width:80%; margin-right:auto; margin-left:auto}");
			
			cr.addFileRubric(fr);;

			break;
		case 4:

			fr = new FileRubric("index.html");
			fr.addUnitRubric("<img(\\s*)src(\\D+)619(\\D+)117",2, "<img src=\"javalogo.gif\" alt=\"Javajam Coffeehouse\" width=\"619\" height=\"117\" /> is incorrect");
			fr.addUnitRubric("<img(\\s*)src(\\D+)156(\\D+)333",2, "<img src=\"windingroad.jpg\" alt=\"Winding road through the woods\" width=\"156\" height=\"333\" /> is incorrect");
			
			cr.addFileRubric(fr);;

			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("(\\s*)body(\\s*)\\{(\\s*)background-image(\\s*):(\\s*)url",2," body selector is incorrect, it should be, body {background-image:url(background.gif)}");
			fr.addUnitRubric("(\\s*)#(\\s*)wrapper(\\s*)\\{(\\s*)width(\\s*):([^;]+);(\\s*)margin-right(\\s*):([^;]+);(\\s*)margin-left(\\s*):([^;]+);(\\s*)background-color(\\s*):([^;]+);(\\s*)min-width(\\s*):([^;]+);(\\s*)max-width(\\s*):([^;]+);(\\s*)box-shadow(\\s*):([^;]+)",2,"#wrapper selector is incorrect, it should be, #wrapper { width: 80%; margin-right: auto;margin-left: auto;background-color: #ffffcc;min-width: 700px;max-width: 1024px;box-shadow: 3px 3px 3px #333333;}");
			fr.addUnitRubric("(\\s*)h1(\\s*)\\{(\\s*)line-height",2,"h1 selector is incorrect, it should be, h1 {line-height: 200%;}");
			fr.addUnitRubric("(\\s*)h2(\\s*)\\{(\\s*)background-color(\\s*):([^;]+);(\\s*)font-size(\\s*):([^;]+);(\\s*)padding-left(\\s*):([^;]+);(\\s*)padding-bottom",2,"h2 selector is incorrect, it should be, h2 { background-color: #ccaa66;font-size: 1.2em;padding-left: 10px;padding-bottom: 5px;}");
			fr.addUnitRubric("(\\s*)main(\\s*)\\{(\\s*)padding",2," main selector is incorrect, it should be, main {padding:25px;}");
			fr.addUnitRubric("(\\.)details(\\s*)\\{(\\s*)padding-left(\\s*):([^;]+);(\\s*)padding-right", 2,"details class selector is incorrect, it should be .details { padding-left: 20%; padding-right: 20%;}");
			fr.addUnitRubric("(\\s*)img(\\s*)\\{(\\s*)border-style",2,"img selector is incorrect, it should be, img {border-style:none;}");
			
			cr.addFileRubric(fr);;

			fr = new FileRubric("music.html");
			fr.addUnitRubric("<p(\\s+)class(\\s*)=(\\s*)\"details", 2, "<p class=\"details\"> is missing");
			
			cr.addFileRubric(fr);;
			break;
		case 5:

			fr = new FileRubric("stylesch5.css");
			fr.addUnitRubric("body(\\D+)11", 2,"body {font-family: Arial, Centaur,\"Century Gothic\";font-size: 11pt;}");
			fr.addUnitRubric("(\\s*)p(\\s*)\\{(\\s*)margin-left(\\D+)5pt(\\s*);", 2, "p {margin-left: 5pt; margin-right: 5pt;}");
			fr.addUnitRubric("(\\s*)a(\\s*)\\{(\\s*)text-decoration([^;]+);(\\s*)color(\\s*):(\\s*)#030a24",2, "a {text-decoration: none; color: #030a24;}");
			fr.addUnitRubric("(\\s*)img(\\s*)\\{(\\s*)border-color(\\s*):(\\s*)transparent;",2,"img{border-color: transparent;}");
			fr.addUnitRubric("boldword", 2, ".boldword {color: #030a24; font-weight: bolder;}");
			fr.addUnitRubric("list-style-type(\\s*):(\\s*)none(\\s*)", 2, "li.navlist {display: inline; list-style-type: none;}");
			fr.addUnitRubric("a(\\s*):(\\s*)hover(\\s*)\\{(\\s*)background(\\s*)([^;]+);(\\s*)color(\\s*):(\\s*)#d0a4bb(\\s*);", 2,"a:hover {background: #030a24; color: #d0a4bb;}");

			cr.addFileRubric(fr);;

			fr = new FileRubric("Tahanna.html");
			fr.addUnitRubric("<title>(\\s*)Lake Tahanna Tourism(\\s*)</title>", 2,"<title>Lake Tahanna Tourism</title>");
			fr.addUnitRubric("(\\s*)href(\\s*)=(\\s*)\"stylesch5", 2,"<link rel=\"stylesheet\" type=\"text/css\" href=\"stylesch5.css\" />");
			fr.addUnitRubric("<img(\\s+)src(\\D+)774(\\D+)434([^L]+)Lake([^u]+)usemap(\\s*)=(\\s*)\"#menubar\"",6,"<img src=\"tahanna.jpg\" width=\"774\" height=\"434\" alt=\"Lake Tahanna\" usemap=\"#menubar\" />");
			fr.addUnitRubric("<span(\\s+)class(\\s*)=(\\s*)\"boldword\"(\\s*)>(\\s*)Lake Tahanna(\\s*)<", 1,"<span class=\"boldword\">Lake Tahanna</span>");
			fr.addUnitRubric("<span(\\s+)class(\\s*)=(\\s*)\"boldword\"(\\s*)>(\\s*)Lake Tahanna Tourism(\\s*)", 1,"<span class=\"boldword\">Lake Tahanna Tourism</span>");
			fr.addUnitRubric("<span(\\s+)style(\\s*)=(\\s*)\"text-decoration(\\s*):(\\s*)underline(\\s*)\"(\\s*)>(\\s*)tahanna@", 4,"<span style=\"text-decoration: underline\">tahanna@isp.com</span>");
			fr.addUnitRubric("&reg;", 2,"&reg;");
			fr.addUnitRubric("<li(\\s+)class(\\s*)=(\\s*)\"navlist\"(\\s*)>(\\s*)<(\\s*)a(\\s+)href(\\s*)=(\\s*)\"tahanna", 2,"<li class=\"navlist\"><a href=\"tahanna.html\">Home</a>");
			fr.addUnitRubric("<li(\\s+)class(\\s*)=(\\s*)\"navlist\"(\\s*)>(\\s*)<(\\s*)a(\\s+)href(\\s*)=(\\s*)\"skiing", 2,"<li class=\"navlist\"><a href=\"skiing.html\">Skiing</a>");
			fr.addUnitRubric("<li(\\s+)class(\\s*)=(\\s*)\"navlist\"(\\s*)>(\\s*)<(\\s*)a(\\s+)href(\\s*)=(\\s*)\"boating", 2,"<li class=\"navlist\"><a href=\"boating.html\">Boating</a>");
			fr.addUnitRubric("<li(\\s+)class(\\s*)=(\\s*)\"navlist\"(\\s*)>(\\s*)<(\\s*)a(\\s+)href(\\s*)=(\\s*)\"dining", 2,"<li class=\"navlist\"><a href=\"dining.html\">Dining</a>");
			fr.addUnitRubric("<area(\\s+)shape(\\s*)=(\\s*)\"rect\"(\\s*)coords(\\s*)=(\\s*)\"195([^h]+)href(\\s*)=(\\s*)\"tahanna", 2,"<area shape=\"rect\" coords=\"195,63,247,82\" href=\"tahanna.html\" alt=\"Home\" />");
			fr.addUnitRubric("<area(\\s+)shape(\\s*)=(\\s*)\"rect\"(\\s*)coords(\\s*)=(\\s*)\"305([^h]+)href(\\s*)=(\\s*)\"skiing", 2,"<area shape=\"rect\" coords=\"305,63,354,82\" href=\"skiing.html\" alt=\"Skiing\" />");
			fr.addUnitRubric("<area(\\s+)shape(\\s*)=(\\s*)\"rect\"(\\s*)coords(\\s*)=(\\s*)\"413([^h]+)href(\\s*)=(\\s*)\"boating", 2,"<area shape=\"rect\" coords=\"413,63,470,82\" href=\"boating.html\" alt=\"Boating\" />");
			fr.addUnitRubric("<area(\\s+)shape(\\s*)=(\\s*)\"rect\"(\\s*)coords(\\s*)=(\\s*)\"521([^h]+)href(\\s*)=(\\s*)\"dining", 2,"<area shape=\"rect\" coords=\"521,63,574,82\" href=\"dining.html\" alt=\"Dining\" />");

			cr.addFileRubric(fr);;

			break;
		case 6:
			fr = new FileRubric("index.html");
			fr.addUnitRubric("html5\\.js", 2,"<script src=\" http://html5shim.googlecode.com/svn/trunk/html5.js \"></script> is missing");
			fr.addUnitRubric("<header>\\s*<h1>", 2,"<header><h1> is missing");
			fr.addUnitRubric("<ul>", 2,"<ul> is missing");
			fr.addUnitRubric("<li>(\\s*)(\\w.*)</li>", 2,"<li></li> is missing");
			fr.addUnitRubric("floatright",2, "<img src=\"windingroad.jpg\" alt=\"winding road through the woods\" width=\"156\" height=\"333\" class=\"floatright\" /> is incorrect");
			
			cr.addFileRubric(fr);
			
			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("(\\s*)header(\\s*)\\{(\\s*)background-color(\\s*):([^;]+);(\\s*)color(\\s*):([^;]+);(\\s*)background-image(\\s*):(\\s*)url([^;]+);(\\s*)background-position(\\s*):([^;]+);(\\s*)background-repeat(\\s*):([^;]+);(\\s*)line-height",1," header selector is incorrect, it should be, header { background-color: #ccaa66;color: #000000;background-image: url(javalogo.gif);background-position: center;background-repeat: no-repeat;line-height: 100px;");
			fr.addUnitRubric("(\\s*)h1(\\s*)\\{(\\s*)margin:([^;]+);(\\s*)text-indent:([^;]+);(\\s*)white-space:([^;]+);(\\s*)overflow",1,"h1 selector is incorrect, it should be, h1 { margin: 0;text-indent: 100%;white-space: nowrap;overflow: hidden;}");
			fr.addUnitRubric("(\\s*)h2(\\s*)\\{(\\s*)text-transform(\\s*):([^;]+);(\\s*)background-color(\\s*):([^;]+);(\\s*)color(\\s*):([^;]+);(\\s*)font-size(\\s*):([^;]+);(\\s*)border-bottom(\\s*):([^;]+);(\\s*)padding(\\s*):([^;]+);(\\s*)margin-right(\\s*):([^;]+);(\\s*)clear",1,"h2 selector is incorrect, it should be, h2 { text-transform: uppercase;background-color: #ffffcc;color: #663300;font-size: 1.2em;border-bottom: 1px solid #000000;padding: 5px 0 0 5px;margin-right: 20px;clear: left;");
			fr.addUnitRubric("(\\s*)nav(\\s*)\\{(\\s*)text-align:([^;]+);(\\s*)float(\\s*):([^;]+);(\\s*)width(\\s*):([^;]+);(\\s*)font-weight(\\s*):([^;]+);(\\s*)padding-top",1,"nav selector is incorrect, it should be, nav { text-align: center;float: left;width: 100px;font-weight: bold;padding-top: 10px;");
			fr.addUnitRubric("(\\s*)nav(\\s+)a(\\s*)\\{(\\s*)text-decoration:([^;]+);(\\s*)padding-bottom",1," nav a selector is incorrect, it should be, nav a { text-decoration: none; padding-bottom: 15px;");
			fr.addUnitRubric("(\\s*)nav(\\s+)a(\\s*):(\\s*)link(\\s*)\\{(\\s*)color",2," nav a:link selector is incorrect, it should be, nav a:link { color: #996633; }");
			fr.addUnitRubric("(\\s*)nav(\\s+)a(\\s*):(\\s*)visited(\\s*)\\{(\\s*)color",2," nav a:visited selector is incorrect, it should be, nav a:visited { color: #ccaa66; }");
			fr.addUnitRubric("(\\s*)nav(\\s+)a(\\s*):(\\s*)hover(\\s*)\\{(\\s*)color",2," nav a:hover selector is incorrect, it should be, nav a:hover { color: #330000; }");
			fr.addUnitRubric("(\\s*)nav(\\s+)ul(\\s*)\\{(\\s*)list-style-type",2," nav ul selector is incorrect, it should be, nav ul { list-style-type: none; }");
			fr.addUnitRubric("(\\s*)main(\\s*)\\{(\\s*)padding:([^;]+);(\\s*)margin-left:([^;]+);(\\s*)background-color:([^;]+);(\\s*)color",1," main selector is incorrect, it should be, main { padding: 10px 20px 30px 20px;margin-left: 150px;background-color: #f2eab7;color: #000000;");
			fr.addUnitRubric("(\\.)floatright(\\s*)\\{(\\s*)float(\\s*):([^;]+);(\\s*)padding-left", 1,"floatright class selector is incorrect, it should be .floatright { float: right;padding-left: 20px;}");
			fr.addUnitRubric("(\\.)floatleft(\\s*)\\{(\\s*)float(\\s*):([^;]+);(\\s*)padding-right:([^;]+);(\\s*)padding-bottom", 1,"floatleft class selector is incorrect, it should be .floatleft { float: left;padding-right: 20px;padding-bottom: 20px;}");
			fr.addUnitRubric("(\\.)details(\\s*)\\{(\\s*)padding-left(\\s*):([^;]+);(\\s*)padding-right(\\s*):([^;]+);overflow", 1,"details class selector is incorrect, it should be .details { padding-left: 20%; padding-right: 20%;overflow:auto}");
			fr.addUnitRubric("(\\s*)img(\\s*)\\{(\\s*)border-style",2,"img selector is incorrect, it should be, img {border-style:none;}");
			fr.addUnitRubric("(\\s*)footer(\\s*)\\{(\\s*)background-color(\\s*):([^;]+);(\\s*)color(\\s*):([^;]+);(\\s*)font-size(\\s*):([^;]+);(\\s*)font-style:([^;]+);(\\s*)text-align:([^;]+);(\\s*)padding:([^;]+);(\\s*)clear",1,"footer selector is incorrect, it should be, footer { background-color: #ccaa66;color: #000000;font-size: .60em;font-style: italic;text-align: center;padding: 20px;clear: both;}");
			fr.addUnitRubric("(\\s*)footer(\\s+)a(\\s*):(\\s*)link(\\s*)\\{(\\s*)color",2," footer a:link selector is incorrect, it should be, footer a:link { color: #ffffcc; }");
			fr.addUnitRubric("(\\s*)footer(\\s+)a(\\s*):(\\s*)visited(\\s*)\\{(\\s*)color",2," footer a:visited selector is incorrect, it should be, footer a:visited { color: #f2eab7; }");
			fr.addUnitRubric("(\\s*)footer(\\s+)a(\\s*):(\\s*)hover(\\s*)\\{(\\s*)color",2," footer a:hover selector is incorrect, it should be, footer a:hover { color: #330000; }");
			fr.addUnitRubric("(\\s*)header(\\s*),(\\s*)main(\\s*),(\\s*)nav(\\s*),(\\s*)footer(\\s*)\\{(\\s*)display",2,"header, main, nav, footer  selector is incorrect, it should be, header, main, nav, footer { display: block; }");
			
			
			cr.addFileRubric(fr);
			fr = new FileRubric("music.html");
			fr.addUnitRubric("floatleft",2, "<img src=\"melanie.jpg\" ... class=\"floatleft\" /> is incorrect");
			
			cr.addFileRubric(fr);
						

			break;
		case 7:

			
			fr = new FileRubric("index.html");
			fr.addUnitRubric("viewport", 1,"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">  is missing");
			fr.addUnitRubric("device-width", 1,"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">  is missing");
			fr.addUnitRubric("initial-scale", 1,"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">  is missing");
			fr.addUnitRubric("id(\\s*)=(\\s*)\"mobile", 2,"<a id=\"mobile\" href=\"tel:888-555-5555\">1-888-555-5555</a> is missing");
			fr.addUnitRubric("id(\\s*)=(\\s*)\"desktop",2, "<span id=\"desktop\">1-888-555-5555</span> is missing");
		
			cr.addFileRubric(fr);;

			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("(\\s*)header(\\s*)\\{(\\s*)background-color(\\s*):([^;]+);(\\s*)color(\\s*)",2," header selector is incorrect, it should be, header { background-color: #ccaa66;color: #000000;background-image: url(javalogo.gif);background-position: center;background-repeat: no-repeat;line-height: 100px;");
			fr.addUnitRubric("max-width(\\s*):(\\s*)1024",2," wrapper id selector is incorrect, it should be, wrapper { ...;max-width:1024px;... }");
			fr.addUnitRubric("(\\.)floatright(\\s*)\\{(\\s*)float(\\s*):(\\s*)right", 2,"floatright class selector is incorrect, it should be .floatright { float: right;}");
			fr.addUnitRubric("(\\s*)#(\\s*)mobile(\\s*)\\{(\\s*)display(\\s*):(\\s*)none",2,"#mobile selector is incorrect, it should be, #mobile { display: none; }");
			fr.addUnitRubric("(\\s*)#(\\s*)desktop(\\s*)\\{(\\s*)display(\\s*):(\\s*)inline",2,"#desktop selector is incorrect, it should be, #desktop { display: inline; }");
			fr.addUnitRubric("(\\s*)@(\\s*)media(\\s+)only(\\s+)screen(\\s+)and(\\s+)\\((\\s*)max-width(\\s*):(\\s*)1024",2,"@media selector is incorrect, it should be, @media only screen and (max-width: 1024px)");
			fr.addUnitRubric("(\\s*)body(\\s*)\\{(\\s*)margin(\\s*):([^;]+);(\\s*)background-image",2," body selector is incorrect, it should be, body { margin: 0; background-image: none; }");
			fr.addUnitRubric("(\\s*)#(\\s*)wrapper(\\s*)\\{(\\s*)width(\\s*):([^;]+);(\\s*)min-width(\\s*):([^;]+);(\\s*)margin(\\s*):([^;]+);(\\s*)box-shadow",1,"#wrapper selector is incorrect, it should be, #wrapper {width: auto; min-width: 0; margin: 0; box-shadow: none;}");
			fr.addUnitRubric("(\\s*)header(\\s*)\\{(\\s*)background-image(\\s*):([^;]+);(\\s*)background-position(\\s*):([^;]+);(\\s*)background-repeat(\\s*):([^;]+);(\\s*)height",2," header selector is incorrect, it should be, header {background-image: url(javalogomobile.gif);background-position: center;background-repeat: no-repeat;height: 80px; }");
			fr.addUnitRubric("(\\s*)nav(\\s*)\\{(\\s*)float(\\s*):([^;]+);(\\s*)width(\\s*):([^;]+);(\\s*)padding-top:([^;]+);(\\s*)margin-top",1,"nav selector is incorrect, it should be, nav { float: none; width: auto; padding-top: 0; margin-top: 0;}");
			fr.addUnitRubric("(\\s*)nav(\\s+)li(\\s*)\\{(\\s*)display(\\s*):(\\s*)inline-block",1," nav li selector is incorrect, it should be, nav li { display: inline-block; }");
			fr.addUnitRubric("(\\s*)nav(\\s+)a(\\s*)\\{(\\s*)padding(\\s*):([^;]+);(\\s*)font-size(\\s*):([^;]+);(\\s*)width(\\s*):([^;]+);(\\s*)font-weight(\\s*):([^;]+);(\\s*)border-style",1," nav a selector is incorrect, it should be, nav a { padding: 1em; font-size: 1.3em; width: 8em; font-weight: bold; border-style: none; }");
			fr.addUnitRubric("(\\s*)nav(\\s+)ul(\\s*)\\{(\\s*)padding(\\s*):([^;]+);(\\s*)margin",2," nav ul selector is incorrect, it should be, nav ul { padding: 0; margin: 0; }");
			fr.addUnitRubric("(\\s*)main(\\s*)\\{(\\s*)padding:([^;]+);(\\s*)margin(\\s*):([^;]+);(\\s*)font-size",1," main selector is incorrect, it should be, main { padding: 2em; margin: 0; font-size: 90%; }");
			
			fr.addUnitRubric("(\\s*)@(\\s*)media(\\s+)only(\\s+)screen(\\s+)and(\\s+)\\((\\s*)max-width(\\s*):(\\s*)768",2,"@media selector is incorrect, it should be, @media only screen and (max-width: 768px)");
			fr.addUnitRubric("(\\s*)nav(\\s+)a(\\s*)\\{(\\s*)display(\\s*):([^;]+);(\\s*)padding(\\s*):([^;]+);(\\s*)width(\\s*):([^;]+);(\\s*)border-bottom",1," nav a selector is incorrect, it should be, nav a { display: block; padding: 0.2em; width: auto; border-bottom: 1px solid #330000; }");
			fr.addUnitRubric("(\\s*)nav(\\s+)li(\\s*)\\{(\\s*)display(\\s*):(\\s*)block",1," nav li selector is incorrect, it should be, nav li { display: block; }");
			fr.addUnitRubric("(\\s*)h2(\\s*)\\{(\\s*)padding(\\s*):([^;]+);(\\s*)margin-right",1,"h2 selector is incorrect, it should be, h2 { padding: 0.5em 0 0 0.5em; margin-right: 0.5em; }");
			fr.addUnitRubric("(\\.)details(\\s*)\\{(\\s*)padding-left(\\s*):([^;]+);(\\s*)padding-right", 1,"details class selector is incorrect, it should be .details { padding-left: 0; padding-right: 0;");
			fr.addUnitRubric("(\\.)floatleft(\\s*)\\{(\\s*)padding-left(\\s*):([^;]+);(\\s*)padding-right", 1,"floatleft class selector is incorrect, it should be .floatleft { padding-left:0.5em ; padding-right: 0.5em ;}");
			fr.addUnitRubric("(\\.)floatright(\\s*)\\{(\\s*)display:", 1,"floatright class selector is incorrect, it should be .floatright { display:none ;}");
			fr.addUnitRubric("(\\s*)#(\\s*)mobile(\\s*)\\{(\\s*)display(\\s*):(\\s*)inline",2,"#mobile selector is incorrect, it should be, #mobile { display: inline; }");
			fr.addUnitRubric("(\\s*)#(\\s*)desktop(\\s*)\\{(\\s*)display(\\s*):(\\s*)none",2,"#desktop selector is incorrect, it should be, #desktop { display: none; }");
			
			
			cr.addFileRubric(fr);;

			break;
		case 8:
			fr = new FileRubric("menu.html");
			fr.addUnitRubric("table", 2,"<table> is missing");
			fr.addUnitRubric("<(\\s*)tr(\\s*)>", 2,"<tr> is missing");
			fr.addUnitRubric("<(\\s*)th(\\s*)>", 2,"<th> is missing");
			fr.addUnitRubric("<(\\s*)td(\\s*)>", 2,"<td> is missing");
			
			cr.addFileRubric(fr);;

			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("(\\s*)table(\\s*)\\{(\\s*)margin:([^;]+);(\\s*)width(\\s*):([^;]+);(\\s*)border-spacing:([^;]+);(\\s*)background-color",2,"table selector is incorrect, table { margin: auto;width: 90%; border-spacing: 0;background-color: #ffffcc;");
			
			fr.addUnitRubric("td(\\s*),(\\s*)th(\\s*)\\{(\\s*)padding", 2,"td,th selector is incorrect, td, th { padding: 10px; }");
			fr.addUnitRubric("tr(\\s*):(\\s*)nth-of-type(\\s*)\\((\\s*)odd(\\s*)\\)(\\s*)\\{(\\s*)background-color", 2,"tr selector is incorrect, tr:nth-of-type(odd) { background-color: #ccaa66; }");
			cr.addFileRubric(fr);;

			break;
		case 9:
			fr = new FileRubric("jobs.html");
			fr.addUnitRubric("<form(\\s+)method(\\s*)=(\\s*)\"post\"", 2,"<form method=\"post\", action=\"http://webdevbasics.net/scripts/javajam.php\"> is missing");
			fr.addUnitRubric("<label(\\s+)for(\\s*)=(\\s*)\"myName\"", 2,"<label for=\"myName\">*Name: </label> is missing");
			fr.addUnitRubric("type(\\s*)=(\\s*)\"text", 1,"<input type=\"text\" ... > is missing");
			fr.addUnitRubric("required(\\s*)=(\\s*)\"required", 1,"<input ...required=\"required\"> is missing");
			fr.addUnitRubric("type(\\s*)=(\\s*)\"email", 2,"<input type=\"email\" required=\"required\"> is missing");
			fr.addUnitRubric("<textarea", 2,"<textarea id=\"myExperience\" name=\"myExperience\" rows=\"2\" cols=\"20\" required=\"required\"> is missing");
			fr.addUnitRubric("type(\\s*)=(\\s*)\"submit", 2,"<input type=\"submit\" value=\"Apply Now\" id=\"mySubmit\"> is missing");
			
			cr.addFileRubric(fr);
			
			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("label(\\s*)\\{(\\s*)float(\\s*):([^;]+);(\\s*)display(\\s*):([^;]+);(\\s*)text-align(\\s*):([^;]+);(\\s*)width(\\s*):([^;]+);(\\s*)padding-right",1,"label selector is incorrect, it should be, label  { float: left;display: block;text-align: right;width: 8em;padding-right: 0.5em;");
			fr.addUnitRubric("input(\\s*),(\\s*)textarea(\\s*)\\{(\\s*)display(\\s*):([^;]+);(\\s*)margin-bottom", 2,"input,textarea selector is incorrect, input, textarea { display: block; margin-bottom: 1em;}");
			fr.addUnitRubric("(\\s*)#(\\s*)mySubmit(\\s*)\\{(\\s*)margin-left",2,"#mySubmit selector is incorrect, it should be, #mySubmit { margin-left: 12em; }");
			cr.addFileRubric(fr);
			
			
			break;
		case 10:
			fr = new FileRubric("chapter10-1.html");
			fr.addUnitRubric("function(\\s+)scrollingMsg", 2,"function scrollingMsg");
			fr.addUnitRubric("window.setTimeout",2,"function setTimeout()");
			fr.addUnitRubric("function(\\s+)validSalesAmt", 2,"function validSalesAmt");
			fr.addUnitRubric("parseInt",2,"function parseInt()");
			fr.addUnitRubric("isNaN",2,"function isNaN()");
			fr.addUnitRubric("alert",2,"function alert()");
			fr.addUnitRubric("focus",2,"function focus()");
			fr.addUnitRubric("function(\\s+)CalcLoanAmt", 2,"function CalcLoanAmt");
			fr.addUnitRubric("parseFloat",2,"function parseFloat()");
			fr.addUnitRubric("function(\\s+)monthlyPmt", 2,"function monthlyPmt");
			fr.addUnitRubric("Math.pow",2,"function Math.pow()");
			fr.addUnitRubric("toFixed",2,"function toFixed()");
			fr.addUnitRubric("function(\\s+)dollarFormat", 2,"function dollarFormat");
			fr.addUnitRubric("split",2,"function split()");
			fr.addUnitRubric("function(\\s+)popUpNotice", 2,"function popUpNotice");
			fr.addUnitRubric("open",2,"function open()");
			fr.addUnitRubric("function(\\s+)copyRight", 2,"function copyRight");
			fr.addUnitRubric("<body(\\s+)onLoad(\\s*)=(\\s*)\"scrollingMsg([^;]+);(\\s*)popUpNotice([^;]+);(\\s*)copyRight",2,"<body onLoad=\"scrollingMsg(); popUpNotice(); copyRight()\">");
			fr.addUnitRubric("onBlur(\\s*)=(\\s*)\"validSalesAmt",2,"<td class=\"align-left\"><input name=\"DownPayment\" type=\"text\" id=\"DownPayment\" size=\"9\" onBlur=\"validSalesAmt()\" /></td>");
			fr.addUnitRubric("onClick(\\s*)=(\\s*)\"CalcLoanAmt",2," <input name=\"button\" type=\"button\" value=\"Calculate\" onClick=\"CalcLoanAmt();\" />");
			cr.addFileRubric(fr);
			break;
		case 11:
			fr = new FileRubric("music.html");
			fr.addUnitRubric("<audio", 5,"<audio> is missing");
			fr.addUnitRubric("audio/mpeg", 5,"<source src=\"melanie.mp3\" type=\"audio/mpeg\"> is missing");
			fr.addUnitRubric("audio/ogg", 5,"<source src=\"melanie.ogg\" type=\"audio/ogg\"> is missing");
			fr.addUnitRubric("href(\\s*)=(\\s*)\"melanie.mp3", 5,"<a href=\"melanie.mp3\">Music by Melanie</a> is missing");
			fr.addUnitRubric("href(\\s*)=(\\s*)\"greg.mp3", 5,"<a href=\"greg.mp3\">Music by Greg</a> is missing");
			
			cr.addFileRubric(fr);;
			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("audio(\\s*)\\{(\\s*)display(\\s*):([^;]+);(\\s*)margin-top",10,"audio tag is incorrect, tt should be, audio { margin-top: 1em;display: block;");
			
			cr.addFileRubric(fr);;
			
		case 12:
			fr = new FileRubric("gear.html");
			fr.addUnitRubric("floatleft", 2,"<img src=\"javashirt.jpg\" class=\"floatleft\" alt=\"JavaJam Shirt\" height=\"186\" width=\"200\"> is incorrect");
			fr.addUnitRubric("method(\\s*)=(\\s*)\"post", 2,"<form method=\"post\" action=\"http://www.webdevfoundations.net/scripts/cart.asp\"> is incorrect");
			fr.addUnitRubric("type(\\s*)=(\\s*)\"hidden", 2,"<input type=\"hidden\"....> is missing");
			fr.addUnitRubric("type(\\s*)=(\\s*)\"submit", 2,"<input type=\"submit\" ...> is missing");
			fr.addUnitRubric("clearleft", 2,"<br class=\"clearleft\"> is missing");
			
			cr.addFileRubric(fr);;
			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("\\.clearleft(\\s*)\\{(\\s*)clear(\\s*):([^;]+);(\\s*)margin",2,"clearleft class tag is incorrect, it should be, .clearleft{ clear: left;margin-bottom: 1em;}");
			
			cr.addFileRubric(fr);;
			
			break;
		case 13:
			fr = new FileRubric("index.html");
			fr.addUnitRubric("name(\\s*)=\"description", 2,"<meta name=\"description\"....> is missing");
			cr.addFileRubric(fr);
			fr = new FileRubric("menu.html");
			fr.addUnitRubric("name(\\s*)=\"description", 2,"<meta name=\"description\"....> is missing");
			cr.addFileRubric(fr);
			fr = new FileRubric("jobs.html");
			fr.addUnitRubric("name(\\s*)=\"description", 2,"<meta name=\"description\"....> is missing");
			cr.addFileRubric(fr);
			fr = new FileRubric("music.html");
			fr.addUnitRubric("name(\\s*)=\"description", 2,"<meta name=\"description\"....> is missing");
			cr.addFileRubric(fr);
			fr = new FileRubric("gear.html");
			fr.addUnitRubric("name(\\s*)=\"description", 2,"<meta name=\"description\"....> is missing");
			cr.addFileRubric(fr);
	/*		
			fr = new FileRubric("javajam.css");
			fr.addUnitRubric("header(\\s*),(\\s*)main(\\s*),(\\s*)nav(\\s*),(\\s*)footer(\\s*)\\{(\\s*)display(\\s*):(\\s*)block",4,"header,nav,footer tag is incorrect, it should be, header, nav, footer { display: block; }");
			cr.addFileRubric(fr);;
	*/		
			break;
		case 14:
			fr = new FileRubric("index.html");
			fr.addUnitRubric("<script", 5,"<script> is missing");
			cr.addFileRubric(fr);
			fr = new FileRubric("music.html");
			fr.addUnitRubric("onmouseover", 5,"<script> is missing");
			cr.addFileRubric(fr);
			
			break;
		default:
				break;
		}

		return cr;

	}
}
