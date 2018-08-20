
# [可扩展Web架构和分布式系统](http://www.aosabook.org/en/distsys.html)

## 1. 分布式系统设计的原则

- Availability：高可用在分布式系统中需要仔细斟酌，如关键业务组件的冗余、系统错误的快速恢复、问题发生时的优雅降级等
- Performance: 快速响应，低延迟是关键
- Reliability
- Scalability: Scalability can refer to many different parameters of the system: how much additional traffic can it handle, how easy is it to add more storage capacity, or even how many more transactions can be processed
- Manageability
- Cost: 软硬件、系统部署和维护、开发、培训等

## 2. 基本原理

做系统架构时，一些事情需要考虑：what are the right pieces, how these pieces fit together, and what are the right tradeoffs. 在真正需要规模化之前投资不是一个明智的选择，而在设计上的深谋远虑才能在将来为我们节省时间和资源。

核心因素：`services(服务)`, `redundancy(冗余)`, `partitions(分区一致性)`, `handling failure`，每一个因素都涉及到选择和妥协。

### 2.1 图像托管应用程序 Image Hosting Application

> There are challenges in building an architecture that is cost-effective, highly available, and has low latency (fast retrieval).
> the upload to be efficient and  having very fast delivery
> storage scalability needs to be considered
> There needs to be low latency for image downloads/requests
> data reliability for images
> The system should be easy to maintain
> the system needs to be cost-effective

#### 2.1.1 Services
SOA, 当考虑可扩展的系统设计时，有助于解耦功能并将系统的每个部分看做具有明确定义的接口服务；将系统解构成一组互补服务的集合解耦了每部分的操作，这种抽象能帮助我们在服务之间建立清晰的关系，依赖的环境和每个服务的消费者；


#### 2.1.2 Redundancy
