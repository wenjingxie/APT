$(document).ready(function(){
	var names = ["Andy", "Andrew", "Bob", "Bobby", "Chuck", "Charles", "David"];
	$('input').autocomplete({source: names});
});