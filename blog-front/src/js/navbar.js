$(document).ready(function($) {
	$('.button-collapse').sideNav({
		menuWidth: 300, // Default is 240
		edge: 'left', // Choose the horizontal origin
		closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
		}
	);
});
$(document).ready(function(){
        var   oUl= document.getElementById("navigation-style");
        var   oLi=oUl.getElementsByTagName('li');
        var  i=0;
        for(i=0;i<oLi.length-1;i++)
        {
                oLi[i].onmouseover=function()
                {
                        for(i=0;i<oLi.length-1;i++)
                        {
                            oLi[i].className="";
                        }
                        this.className="active";

                }

        }
      
           
});

