/**
 * 
 */
var app = {
	init: function(){
		console.log('Document Ready...');
	},
	setMenuActiveState: function(){
		$('nav li.active').removeClass('active');
		$('a[href="' + location.pathname + '"]').closest('li').addClass('active'); 
	}
};

$(document).ready(function(){
	app.init();
	app.setMenuActiveState();
});