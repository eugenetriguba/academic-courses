# Extended Abstract - Virtualization: Virtual Machines & Containers

For this research project, I'll be looking into virtualization and focusing on virtual machines and containers. It will be more of a survey of the research out there in the field. However, I do plan to experiment more with products like VMware or Docker throughout the course of this.

## Basic Outline

I'd like to start off with explaining the concept of virtualization:

 * What is it, where does it come from?
 * What is it used for?
 * The explanation of this could be used as the basis for the particular tools, like virtual machines and containers.

Next, I plan to move into virtual machines. I think it makes more sense to start with them since containers are sometimes described as lightweight virtual machines. However, they would both have similar questions I would want to try to answer such as:

 * What are they? How do they work?
 * How do they interact with the operating system?
 * Why would you use them? What are the benefits?
 * What are things to look out for when using them, security issues?
 * Why use virtual machines over containers and vice versa?
 * Are there any particular tools used with either of them and what for?

For the presentation portion at the end of the semester, it could be interesting if I use about 2/3 of the time explaining the theoretical side and my research and having the other 1/3 be an actual live demo of how to setup a VM or container. The reasoning behind that is so my classmates can walk away from this presentation learning something practical they can use as well as the theory of what is actually happening, when to use which either one, etc.

## Some Key Points

### Virtual Machines
 * VMware seems to be the de-facto in this domain so that is what I would showcase in a presentation.
 * VMs provide strong isolation
 * Save money on hardware, electricity, and save rack space
 * Provide portability, each VM can take its own environment with it
 * Often are used to run legacy applications on Operating Systems that are no longer supported or no longer runs on current hardware.
 * Useful for Software Development
    * Testing the application on various operating systems
    * Virtual machines here remove the need for various physical machines running different Operating Systems

### Containers
 * Docker has been very hyped up lately, and that is what I would want to look into for something that implements containers.
 * Used for container virtualization (essentially a lightweight virtual machine)
 * "Developer Workflow" - trying to help people build containers and apps in them to share among their teammates
 * Aim is a more lightweight and agile compute resource -> Improves scalability
 * Part of the DevOps movement and attempting to integrate the development and production process more rather than just having the development team hand it off to the operations team and nothing works in that environment.

### Cloud
 * Naturally, the cloud is going to come up when talking about these two since that is a big application of both of them. So I assume there will be some time dedicated to this topic, and how they are used in that domain.
 * The cloud is essentially computation and storage is outsourced to a data center
 * Because of the isolation provided by VMs, multiple clients can share a single physical machine.
 * However, containers use less resources and are more lightweight. Therefore, they are also more scalable.


### Bibliography

 [1] A. Tanenbaum and H. Bos, Modern Operating Systems, 4th ed. Pearson, 2014, pp. 471-516.

 [2] F. Cohen, "The Virtualization Solution", IEEE Security & Privacy Magazine, vol. 8, no. 3, pp. 60-63, 2010. Available: 10.1109/msp.2010.108.

 [3] M. Pearce, S. Zeadally and R. Hunt, "Virtualization: Issues, security threats, and solutions", ACM Computing Surveys, vol. 45, no. 2, pp. 1-39, 2013. Available: 10.1145/2431211.2431216.

 [4] S. Vaughan-Nichols, "Virtualization Sparks Security Concerns" in Computer, vol. 41, no. 08, pp. 13-15, 2008. Available: 10.1109/MC.2008.276

 [5] C. Anderson, "Docker [Software engineering]" in IEEE Software, vol. 32, no. 03, pp. 102-c3, 2015. Available: 10.1109/MS.2015.62

 [6] T. Combe, A. Martin and R. Di Pietro, "To Docker or Not to Docker: A Security Perspective" in IEEE Cloud Computing, vol. 3, no. 05, pp. 54-62, 2016. Available: 10.1109/MCC.2016.100

 [7] V. Medina and J. García, "A survey of migration mechanisms of virtual machines", ACM Computing Surveys, vol. 46, no. 3, pp. 1-33, 2014. Available: 10.1145/2492705.

 [8] D. Samant and U. Bellur, "Handling Boot Storms in Virtualized Data Centers—A Survey", ACM Computing Surveys, vol. 49, no. 1, pp. 1-36, 2016. Available: 10.1145/2932709.