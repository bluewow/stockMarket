window.addEventListener("message", function (e) {
	if (e.data[0] == "selectPhoto"){
		let profile = document.querySelector("#myRank .profileImg img");
		profile.src = "/resource/images/profile/"+ e.data[1] +".png";
		
		let rank = profile.parentNode.previousElementSibling.innerText;
		
		if(rank > 50) return;
		
		let profile2 = document.querySelectorAll("#contaner>div:first-child tbody tr td.name");
		let id = profile.parentNode.nextElementSibling.innerText;
		
		for(let i = 0; i < profile2.length; i++){
			if(id == profile2[i].innerText)
				profile2[i].previousElementSibling.firstChild.src = "/resource/images/profile/"+ e.data[1] +".png";			
		}
	}
});
