package cn.nicollcheng.ip2region;

import org.nutz.plugins.ip2region.DataBlock;
import org.nutz.plugins.ip2region.DbSearcher;
import org.springframework.beans.factory.DisposableBean;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/8/3 9:34.
 */
public class Ip2regionTemplate implements DisposableBean {
    private final DbSearcher dbSearcher;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Ip2regionTemplate(DbSearcher dbSearcher) {
        this.dbSearcher = dbSearcher;
    }

    /**
     * get the region with a int ip address with memory binary search algorithm
     *
     * @param ip：int ip address
     * @return {@link DataBlock} instance
     * @throws IOException if reader db file error
     */
    public DataBlock memorySearch(long ip) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.memorySearch(ip);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * get the region through the ip address with memory binary search algorithm
     *
     * @param ip：string ip address
     * @return {@link DataBlock} instance
     * @throws IOException if reader db file error
     */
    public DataBlock memorySearch(String ip) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.memorySearch(ip);
        } finally {
            lock.readLock().unlock();
        }
    }


    /**
     * get by index ptr
     *
     * @param ptr：index ptr
     * @return {@link DataBlock} instance
     * @throws IOException if reader db file error
     */
    public DataBlock getByIndexPtr(long ptr) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.getByIndexPtr(ptr);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * get the region with a int ip address with b-tree algorithm
     *
     * @param ip：int ip address
     * @return {@link DataBlock} instance
     * @throws IOException if reader db file error
     */
    public DataBlock btreeSearch(long ip) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.btreeSearch(ip);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * get the region through the ip address with b-tree search algorithm
     *
     * @param ip：string ip address
     * @return {@link DataBlock} instance
     * @throws IOException if reader db file error
     */
    public DataBlock btreeSearch(String ip) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.btreeSearch(ip);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * get the region with a int ip address with binary search algorithm
     *
     * @param ip：int ip address
     * @return {@link DataBlock} instance
     * @throws IOException if reader db file error
     */
    public DataBlock binarySearch(long ip) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.binarySearch(ip);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * get the region through the ip address with binary search algorithm
     *
     * @param ip：string ip address
     * @return {@link DataBlock} instance
     * @throws IOException if reader db file error
     */
    public DataBlock binarySearch(String ip) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.binarySearch(ip);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * get region by ip
     * @param ip：string ip address
     * @return region string
     * @throws IOException
     */
    public String getRegion(String ip) throws IOException {
        try {
            lock.readLock().lock();
            return dbSearcher.memorySearch(ip).getRegion();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * get RegionInfo by string ip address
     * @param ip：：string ip address
     * @return {@link RegionInfo}
     * @throws IOException
     */
    public RegionInfo getRegionInfo(String ip) throws IOException {
        try {
            lock.readLock().lock();
            return new RegionInfo(dbSearcher.memorySearch(ip).getRegion().split("\\|"));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void destroy() throws Exception {
        dbSearcher.close();
    }
}
