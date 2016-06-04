$(document).ready(function($) {
	$('.button-collapse').sideNav({
		menuWidth: 300, // Default is 240
		edge: 'left', // Choose the horizontal origin
		closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
		}
	);
});
/*   右侧弹出窗口 */ 
$(document).ready(function(){

               var  oDiv=document.getElementById("sideBarmenu");
               var  oSideBar=document.getElementById("sideBar-user");
               oDiv.onmouseover=oSideBar.onmouseover=function()
               {
                     oDiv.style.display="block";
               }
                oDiv.onmouseout=oSideBar.onmouseout=function()
               {
                     oDiv.style.display="none";
               }
         
         
               


});
      
           

    



