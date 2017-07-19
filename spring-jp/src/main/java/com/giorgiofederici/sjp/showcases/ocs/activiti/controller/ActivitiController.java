package com.giorgiofederici.sjp.showcases.ocs.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.giorgiofederici.sjp.showcases.ocs.domain.dto.ShoppingCartDto;
import com.giorgiofederici.sjp.showcases.ocs.domain.entity.OcsProduct;
import com.giorgiofederici.sjp.showcases.ocs.service.OcsProductService;

@Controller
@SessionAttributes("shoppingCartDto")
@RequestMapping(value = "showcases/ocs")
public class ActivitiController {

	@Autowired
	private OcsProductService productService;

	@Autowired
	private ProcessEngine processEngine;

	private String processId;

	@GetMapping(value = "/activiti-shopping")
	public String startOrder(Model model) {

		List<OcsProduct> products = this.productService.findAll();
		model.addAttribute("products", products);

		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (userObj instanceof String) {
			username = (String) userObj;
		} else if (userObj instanceof User) {
			User user = (User) userObj;
			username = user.getUsername();
		} else {
			username = "";
		}

		if (!model.containsAttribute("shoppingCartDto")) {
			model.addAttribute("shoppingCartDto", new ShoppingCartDto());
		}

		ProcessInstance processInstance = getRuntimeService().startProcessInstanceByKey("shopping");
		processId = processInstance.getId();
		execAndCompleteTask(processId, username, "start");

		return "showcases/ocs/activiti-shopping";
	}

	@PostMapping(value = "/activiti-shopping/add/{productId}")
	public String addProduct(Model model, @PathVariable("productId") Integer productId,
			@ModelAttribute("shoppingCartDto") ShoppingCartDto shoppingCartDto) {

		OcsProduct selectedProduct = this.productService.getProductById(productId);
		shoppingCartDto.addProduct(selectedProduct);

		model.addAttribute("shoppingCartDto", shoppingCartDto);

		return "redirect:/showcases/ocs/activiti-shopping";
	}

	@PostMapping(value = "/activiti-shopping/remove/{productId}")
	public String removeProduct(Model model, @PathVariable("productId") Integer productId,
			@ModelAttribute("shoppingCartDto") ShoppingCartDto shoppingCartDto) {

		// Product selectedProduct =
		// this.productService.getProductById(productId);
		shoppingCartDto.removeProduct(productId);

		model.addAttribute("shoppingCartDto", shoppingCartDto);

		return "redirect:/showcases/ocs/activiti-shopping";
	}

	@GetMapping(value = "/activiti-billing")
	public String viewOrder(Model model) {

		List<OcsProduct> products = this.productService.findAll();
		model.addAttribute("products", products);

		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (userObj instanceof String) {
			username = (String) userObj;
		} else if (userObj instanceof User) {
			User user = (User) userObj;
			username = user.getUsername();
		} else {
			username = "";
		}

		if (!model.containsAttribute("shoppingCartDto")) {
			model.addAttribute("shoppingCartDto", new ShoppingCartDto());
		}

		execAndCompleteTask(processId, username, "shopper");
		execAndCompleteServices(processId, username, "buyProduct");

		return "showcases/ocs/activiti-billing";
	}

	@GetMapping(value = "/activiti-shipping")
	public String shipProducts(Model model) {

		List<OcsProduct> products = this.productService.findAll();
		model.addAttribute("products", products);

		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (userObj instanceof String) {
			username = (String) userObj;
		} else if (userObj instanceof User) {
			User user = (User) userObj;
			username = user.getUsername();
		} else {
			username = "";
		}

		if (!model.containsAttribute("shoppingCartDto")) {
			model.addAttribute("shoppingCartDto", new ShoppingCartDto());
		}

		execAndCompleteTask(processId, username, "buyer");
		execAndCompleteServices(processId, username, "chooseProduct");

		return "showcases/ocs/activiti-shipping";
	}

	@GetMapping(value = "/activiti-payment")
	public String payment(Model model) {

		List<OcsProduct> products = this.productService.findAll();
		model.addAttribute("products", products);

		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (userObj instanceof String) {
			username = (String) userObj;
		} else if (userObj instanceof User) {
			User user = (User) userObj;
			username = user.getUsername();
		} else {
			username = "";
		}

		if (!model.containsAttribute("shoppingCartDto")) {
			model.addAttribute("shoppingCartDto", new ShoppingCartDto());
		}

		execAndCompleteTask(processId, username, "payor");
		execAndCompleteServices(processId, username, "deliverProduct");

		return "showcases/ocs/activiti-payment";
	}

	@GetMapping(value = "/activiti-receipt")
	public String receipt(Model model) {

		List<OcsProduct> products = this.productService.findAll();
		model.addAttribute("products", products);

		Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (userObj instanceof String) {
			username = (String) userObj;
		} else if (userObj instanceof User) {
			User user = (User) userObj;
			username = user.getUsername();
		} else {
			username = "";
		}

		if (!model.containsAttribute("shoppingCartDto")) {
			model.addAttribute("shoppingCartDto", new ShoppingCartDto());
		}

		execAndCompleteTask(processId, username, "shipper");
		execAndCompleteTask(processId, username, "end");

		return "showcases/ocs/activiti-receipt";
	}

	private void execAndCompleteTask(String processInstanceId, String username, String taskName) {
		List<Task> tasks = getTaskService().createTaskQuery().taskCandidateUser(username).list();
		Task task = getTask(tasks, processInstanceId, taskName);
		if (task != null) {
			getTaskService().claim(task.getId(), username);
			getTaskService().complete(task.getId());
		}
	}

	private void execAndCompleteServices(String processInstanceId, String cartUser, String serviceName) {

		List<Task> tasks = getTaskService().createTaskQuery().taskCandidateUser(cartUser).list();
		Task task = getTask(tasks, processInstanceId, serviceName);
		if (task != null) {

			getTaskService().claim(task.getId(), cartUser);

			// All Objects for Delegation created and dispatched here
			Map<String, Object> variables = new HashMap<>();
			variables.put("project", "ocp");

			getTaskService().complete(task.getId(), variables);
		}
	}

	private Task getTask(List<Task> tasks, String processInstanceId, String taskId) {
		for (Task task : tasks) {

			if (task.getProcessInstanceId().equals(processInstanceId) && taskId.equals(taskId)) {
				return task;
			}
		}
		return null;
	}

	private RuntimeService getRuntimeService() {
		return processEngine.getRuntimeService();
	}

	private TaskService getTaskService() {
		return processEngine.getTaskService();
	}
}
