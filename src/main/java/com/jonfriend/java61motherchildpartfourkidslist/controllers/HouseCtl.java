package com.jonfriend.java61motherchildpartfourkidslist.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jonfriend.java61motherchildpartfourkidslist.models.UserMdl;
import com.jonfriend.java61motherchildpartfourkidslist.models.HouseMdl;
import com.jonfriend.java61motherchildpartfourkidslist.services.HouseSrv;
import com.jonfriend.java61motherchildpartfourkidslist.services.UserSrv;

@Controller
public class HouseCtl {

	@Autowired
	private HouseSrv houseSrv;
	
	@Autowired
	private UserSrv userSrv;
	
	// display create-new page
	@GetMapping("/house/new")
	public String newHouse(
			@ModelAttribute("house") HouseMdl houseMdl
			, Model model
			, HttpSession session
			) {
		 
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		return "house/create.jsp";
	}
	 
	// process the create-new  
	@PostMapping("/house/new")
	public String addNewHouse(
			@Valid @ModelAttribute("house") HouseMdl houseMdl
			, BindingResult result
			, Model model
			, HttpSession session
			) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		if(result.hasErrors()) {
			return "house/create.jsp";
		}else {

			// this gets the userModel object by calling the user service with the seeion user id
			UserMdl currentUserMdl = userSrv.findById(userId);
			// below sets the userId of the new record with above acquisition.
			houseMdl.setUserMdl( currentUserMdl);
			
			houseSrv.create(houseMdl);
			
			return "redirect:/home";
		}
	}
	
	// view record
	@GetMapping("/house/{id}")
	public String showHouse(
			@PathVariable("id") Long id
			, Model model
			, HttpSession session
			) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		HouseMdl intVar = houseSrv.findById(id);
		
		model.addAttribute("house", intVar);
//		model.addAttribute("assignedCategories", twintwoSrv.getAssignedHouses(intVar));
//		model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedHouses(intVar));
		
		return "house/record.jsp";
	}
	
	// display edit page
	@GetMapping("/house/{id}/edit")
	public String editHouse(
			@PathVariable("id") Long houseId
			, Model model
			, HttpSession session
			) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		// pre-populates the values in the management interface
		HouseMdl intVar = houseSrv.findById(houseId);
		
		model.addAttribute("house", intVar);
//		model.addAttribute("assignedCategories", twintwoSrv.getAssignedHouses(intVar));
//		model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedHouses(intVar));
		
		// records in 'manage-one' interface dropdown
//		List<DojoMdl> intVar3 = dojoSrvIntVar.returnAll();
//		model.addAttribute("dojoList", intVar3); 
		
		return "house/edit.jsp";
	}
	
	// process the edit(s)
//	@PostMapping("/house/{id}/edit")
	// line above replaced by below.  we used to include the path variable id of the record, but we've side stepped that with the ModAtt approach.
	@PostMapping("/house/edit")
	public String PostTheEditHouse(
			@Valid 
			@ModelAttribute("house") HouseMdl houseMdl 
			, BindingResult result
			, Model model
//			, @PathVariable("id") Long houseId
			// above no longer needed, b/c we are using modAtt approach.
			, HttpSession session
			, RedirectAttributes redirectAttributes
			) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// trying here to stop someone from forcing this method when not creator; was working, now no idea.... sigh 7/19 2pm
//		 Long userId = (Long) session.getAttribute("userId"); 
//		 PublicationMdl intVar = houseSrv.findById(houseId);
		
		// System.out.println("in the postMapping for edit..."); 
		// System.out.println("intVar.getUserMdl().getId(): " + intVar.getUserMdl().getId()); 
		// System.out.println("userId: " + userId); 
		
//		if(intVar.getUserMdl().getId() != userId) {
//			redirectAttributes.addFlashAttribute("mgmtPermissionErrorMsg", "Only the creator of a record can edit it.");
//			return "redirect:/publication";
//		}
		
		// below now setting up intVar object by using the getID on the modAtt thing. 
		HouseMdl intVar = houseSrv.findById(houseMdl.getId());
		
		if (result.hasErrors()) { 
			
            Long userId = (Long) session.getAttribute("userId");
            model.addAttribute("user", userSrv.findById(userId));            
//            model.addAttribute("house", intVar);
//            model.addAttribute("assignedCategories", twintwoSrv.getAssignedHouses(intVar));
//            model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedHouses(intVar));

			return "house/edit.jsp";
		} else {
			
//			// this merely returns the joined twintwo records list
//			houseMdl.setTwintwoMdl(twintwoSrv.getAssignedHouses(intVar));
			
			houseMdl.setUserMdl(intVar.getUserMdl());
			// translation of line above: we are reSETTING on the house model object/record the createbyid to that which is GETTING the creatingbyid from the DB... NO LONGER from that silly hidden input. 

			houseSrv.update(houseMdl);
			// below now setting up intVar object by using the getID on the modAtt thing. 
			return "redirect:/house/" + intVar.getId();
		}
	}

	
	// delete house
    @DeleteMapping("/house/{id}")
    public String deleteHouse(
    		@PathVariable("id") Long houseId
    		, HttpSession session
    		, RedirectAttributes redirectAttributes
    		) {
		
		// log out the unauth / deliver the auth use data
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// trying here to stop someone from forcing this method when not creator
		Long userId = (Long) session.getAttribute("userId"); 
		HouseMdl intVar = houseSrv.findById(houseId);
		
		// below is to prevent non-creator from deleting record
//		if(intVar.getUserMdl().getId() != userId) {
//			redirectAttributes.addFlashAttribute("mgmtPermissionErrorMsg", "Only the creator of a record can delete it.");
//			return "redirect:/publication";
//		}

		houseSrv.delete(intVar);
        return "redirect:/home";
    }
	

// end of ctl
}
