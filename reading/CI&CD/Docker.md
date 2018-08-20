
# Docker

### 1. 什么是容器技术？为何要容器技术？容器和虚拟机的区别是？

- Containers virtualize at the operating system level, Hypervisors virtualize at the hardware level.

- Hypervisors abstract the operating system from hardware, containers abstract the application from the operation system.

- Hypervisors consumes storage space for each instance. Containers use a single storage space plus smaller deltas for each layer and thus are much more efficient.

- Containers can boot and be application-ready in less than 500ms and creates new designs opportunities for rapid scaling. Hypervisors boot according to the OS typically 20 seconds, depending on storage speed.

- Containers have built-in and high value APIs for cloud orchestration. Hypervisorshave lower quality APIs that have limited cloud orchestration value.

- 容器则可共享同一个操作系统内核，将应用进程与系统其他部分隔离开

#### 1.1 了解一下虚拟化

[虚拟化](https://www.redhat.com/zh/topics/virtualization)是一种技术，可以利用以往局限于硬件的资源来创建有用的 IT 服务。它让您能够将物理计算机的工作能力分配给多个用户或环境，从而充分利用计算机的所有能力

- 虚拟化的工作原理

一种叫做 Hypervisor （虚拟机监控程序）的软件可有效分隔物理资源，并将这些资源分配给不同虚拟环境（也就是需要这些资源的任务）使用。虚拟机监控程序可能位于操作系统的顶层（例如在便携式计算机上），或者直接安装在硬件上（例如服务器），这是大多数企业使用虚拟化的方式。虚拟机监控程序接管物理资源，并对它们进行划分，以便虚拟环境能够对其进行使用。

**虚拟化**就是通过某种方式隐藏底层物理硬件的过程，从而让多个操作系统可以透明地使用和共享它。虚拟化的好处是减少了能耗、不动资产、冷却和管理成本，使用更少的硬件提供可靠性。

[hypervisor](https://www.ibm.com/developerworks/cn/linux/l-hypervisor/figure2.gif)是一种虚拟化的解决方案，有很久的历史; 仅是一个从其来宾操作系统抽象机器硬件的分层应用程序。通过这种方式，每个来宾操作系统看到的仅是一个 VM 而不是真实的硬件机器。hypervisor 需要少量设施启动来宾操作系统：一个需要驱动的内核映像、一个配置（比如 IP 地址和所需的内存量）、一个磁盘盒一个网络设备。磁盘和网络设备通常映射到机器的物理磁盘和网络设备.最后，需要使用一组来宾操作系统工具启动和管理来宾操作系统。

#### 1.2 KVM - 基于 Linux 的 hypervisor 解决方案

[KVM](https://www.ibm.com/developerworks/cn/linux/l-hypervisor/figure4.gif) 是第一个成为原生 Linux 内核（2.6.20）的一部分的 hypervisor，它是由 Avi Kivity 开发和维护的，现在归 Red Hat 所有.KVM 是作为内核模块实现的，因此 Linux 只要加载该模块就会成为一个hypervisor。KVM 为支持 hypervisor 指令的硬件平台提供完整的虚拟化。这种技术由两个组件实现。第一个是可加载的 KVM 模块，当在 Linux 内核安装该模块之后，它就可以管理虚拟化硬件，并通过 /proc 文件系统公开其功能（见图 4）。第二个组件用于 PC 平台模拟，它是由修改版 QEMU 提供的。QEMU 作为用户空间进程执行，并且在来宾操作系统请求方面与内核协调。

- [http://www.linux-kvm.org/page/Main_Page](http://www.linux-kvm.org/page/Main_Page)
- [关于KVM的详解](http://blog.chinaunix.net/uid-30022178-id-5749329.html)
- [https://www.oschina.net/question/2548918_2149938](https://www.oschina.net/question/2548918_2149938)

#### 1.3 Linux 容器

各种应用日趋复杂，加快开发速度的需求与日俱增。这让您的基础架构、IT 团队以及工作流程压力重重。Linux® 容器可帮助您跨多种环境，有效减缓问题、加速迭代
Linux 容器是能够让您对应用及其整个运行时环境（包括全部所需文件）一起进行打包或隔离的技术。从而让您在不同环境（如开发、测试和生产等环境）之间轻松迁移应用的同时，还可保留应用的全部功能。

与系统其他部分隔离开的一系列进程，从另一个镜像运行，并由该镜像提供支持进程所需的全部文件。容器提供的镜像包含了应用的所有依赖项，因而在从开发到测试再到生产的整个过程中，它都具有可移植性和一致性。

容器是操作系统层虚拟化特征的一种抽象.

Linux 容器的价值在于它能加速开发并满足新出现的业务需求，而并非用于完成这些工作的软件。不要认为容器只限于运行单个应用：您可以使用容器来运行应用或服务的多个部分。而且，您可以使用其他技术（如 [Kubernetes](https://www.redhat.com/zh/topics/containers/what-is-kubernetes)）让容器化应用实现自动化并对这些应用进行编排。由于容器托管了应用逻辑、运行时和依赖项，因此您的容器可以包括所有这一切，或者也可以构建一个应用，其中包含多个用作微服务的容器。

- https://www.redhat.com/zh/topics/virtualization
- https://www.redhat.com/zh/topics/virtualization/what-is-virtualization
- https://www.ibm.com/developerworks/cn/linux/l-hypervisor/

#### 1.4 [容器技术应用及其白皮书](https://blog.csdn.net/wh211212/article/details/53535881?locationNum=4&fps=1)

容器技术发展路径

#### 1.5 [云和虚拟化的比较](https://www.redhat.com/zh/topics/cloud-computing/cloud-vs-virtualization)

### 2. [Docker是什么？](https://docs.docker.com/engine/docker-overview/)

先来看看 [Redhat 的 what is docker](https://www.redhat.com/zh/topics/containers/what-is-docker).

对开发者和系统管理员来说，Docker是一个容器平台，能帮助我们使用容器去开发、部署、运行应用	，我们称之为 containerization 容器化,

> Docker is an open platform for developing, shipping, and running applications

容器有如下特点：

- 灵活，即使非常复杂的应用也可以容器化
- 轻量，容器共享主机资源
- Interchangeable: You can deploy updates and upgrades on-the-fly
- 便携，任何地方都可以运行、部署等
- 扩展性好
- 可堆叠 You can stack services vertically and on-the-fly

容器提供的是一种基于各种 Linux 发行版创建容器镜像的方法、一套管理容器生命周期的 API、与该 API 交互的客户端工具、保存快照的功能、在宿主机之间迁移容器实例的能力。

**延伸阅读**

- [容器简史](https://linux.cn/article-6975-1.html)

#### 2.1 Docker 如何工作

Docker 技术使用 Linux 内核和内核功能（例如 Cgroups 和 namespaces）来分隔进程，以便各进程相互独立运行。它可以独立运行多种进程、多个应用程序，更加充分地发挥基础设施的作用，同时保持各个独立系统的安全性。它可以独立运行多种进程、多个应用程序，更加充分地发挥基础设施的作用，同时保持各个独立系统的安全性。

容器工具（包括 Docker）可提供基于镜像的部署模式。这使得它能够轻松跨多种环境，与其依赖程序共享应用或服务组。Docker 还可在这一容器环境中自动部署应用程序（或者合并多种流程，以构建单个应用程序）。
此外，由于这些工具基于 Linux 容器构建，使得 Docker 既易于使用，又别具一格 —— 它可为用户提供前所未有的高度应用程访问权限、快速部署以及版本控制和分发能力。

#### 2.2 container  and images

An image is a read-only template with instructions for creating a Docker container. Often, an image is based on another image, with some additional customization.You might create your own images or you might only use those created by others and published in a registry. To build your own image, you create a Dockerfile with a simple syntax for defining the steps needed to create the image and run it. Each instruction in a Dockerfile creates a layer in the image. When you change the Dockerfile and rebuild the image, only those layers which have changed are rebuilt. This is part of what makes images so lightweight, small, and fast, when compared to other virtualization technologies.

A container is a runnable instance of an image. You can create, start, stop, move, or delete a container using the Docker API or CLI. A container is defined by its image as well as any configuration options you provide to it when you create or start it. When a container is removed, any changes to its state that are not stored in persistent storage disappear.

#### 2.3 Docker engine

[Docker get started](https://docs.docker.com/get-started/)

**Docker daemon**: The Docker daemon (dockerd) listens for Docker API requests and manages Docker objects such as images, containers, networks, and volumes.A daemon can also communicate with other daemons to manage Docker services.

![engine components flow](https://docs.docker.com/engine/images/engine-components-flow.png)
![Docker architecture](https://docs.docker.com/engine/images/architecture.svg)

#### 2.4 [为什么要用Docker？](https://docs.docker.com/engine/docker-overview/#what-can-i-use-docker-for)

- 模块化
Docker 容器化方法非常关注在不停止整个应用程序的情况下，单独截取部分应用程序进行更新或修复的能力。除了这种基于微服务的方法，您还可以采用与面向服务的架构 (SOA) 类似的使用方法，在多个应用程序间共享进程。

- 层和镜像版本控制
每个 Docker 镜像文件都包含多个层。这些层组合在一起，构成单个镜像。每当镜像发生改变时，一个新的镜像层即被创建出来。用户每次发出命令（例如 run 或 copy）时，都会创建一个新的镜像层。
Docker 重复使用这些层来构建新容器，借此帮助加快流程构建。镜像之间共享中间变化，从而进一步提升速度、 尺寸以及效率。版本控制是镜像层本身自带的能力。每次发生新的更改时，您大都会获得一个内置的更改日志，实现对容器镜像的全盘管控。

- 回滚
每个镜像都拥有多个层。比如说，如果你不喜欢迭代后的镜像版本？完全可以通过回滚，返回之前的版本。这一功能还支持敏捷开发方法，帮助持续实施集成和部署 (CI/CD)，使其在工具层面成为一种现实。

- 快速部署
启动和运行新硬件、实施部署并投入使用，这在过去一般需要数天时间。投入的心力和成本往往也让人不堪重负。基于 Docker 的容器可将部署时间缩短到几秒。通过为每个进程构建容器，您可以快速将这些类似进程应用到新的应用程序中。而且，由于无需启动操作系统即可添加或移动容器，因此大幅缩短了部署时间。除此之外，得益于这种部署速度，您可以轻松无虞、经济高效地创建和销毁容器创建的数据。

Docker 技术是一种更加精细、可控、基于微服务的技术，可为企业提供更高的效率价值。

* [Advances of using docker](https://access.redhat.com/documentation/en-us/red_hat_enterprise_linux/7/html/7.0_release_notes/sect-red_hat_enterprise_linux-7.0_release_notes-linux_containers_with_docker_format-advantages_of_using_docker)

##### docker的限制

Docker 本身非常适合用于管理单个容器。但随着您开始使用越来越多的容器和容器化应用程序，并把它们划分成数百个部分，很可能会导致管理和编排变得非常困难。最终，您需要后退一步，对容器实施分组，以便跨所有容器提供网络、安全、遥测等服务。于是，Kubernetes 应运而生。

使用 Docker，您将获得与传统 Linux 容器不同的类 UNIX 功能，包括可以随同应用程序一起，在容器中使用 cron 或 syslog 之类的进程。当然，在某些事情上面也存在一些限制，例如终止子进程之后，需要清理孙进程，而对于这类事情，传统 Linux 容器会自行处理。我们可以通过在开始时更改配置文件和设置功能消除这些顾虑。当然，这些事情并非十分明显，一眼可见。

除此以外，有些其它 Linux 子系统和设备也未指定命名空间。它们包括 SELinux、Cgroups 以及 /dev/sd* 设备。这意味着，如果攻击者控制了这些子系统，则主机将受到损害。为了保持轻量，主机与容器共享内核，正是因此埋下了安全漏洞的隐患。在这一点上，它与虚拟机不同，后者更加严格地与主机系统保持隔离。

[Docker 守护进程](https://docs.docker.com/engine/reference/commandline/dockerd/)也可能成为安全隐患。为使用和运行 Docker 容器，您很可能需要使用 Docker 守护进程，来为容器提供持续运行时环境。Docker 守护进程需要根权限，所以我们需要特别留意谁可以访问该进程，以及进程驻留在哪个位置。例如，相比公共区域所用的守护进程（例如 Web 服务器），本地守护进程的受攻击面要小得多。

### [剩下的内容看之前的笔记吧](http://note.youdao.com/noteshare?id=efc72f39026c07b3feced49e61c9cc9b&sub=27B8633CFDD04848B1EEC22BEB42A8A9)
### [Container NetworkingFrom Docker to Kubernetes](http://note.youdao.com/noteshare?id=842bc37aa3c23dc9d2718b992a23bc67&sub=B59FA15ECCCA462CA72B362A1018166F)