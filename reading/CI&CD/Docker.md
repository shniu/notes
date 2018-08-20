
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


