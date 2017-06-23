// @author QUINCHO

package com.vpteam.entities;
 
public class Job 
{
    private int id;
    private String images;
    private String description;
    private int jobsContractorId;

    public Job() 
    {
    }

    public Job(int id, String images, String description, int jobsContractorId) 
    {
        this.id = id;
        this.images = images;
        this.description = description;
        this.jobsContractorId = jobsContractorId;
    }
    
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getImages() 
    {
        return images;
    }

    public void setImages(String images) 
    {
        this.images = images;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public int getJobsContractorId() 
    {
        return jobsContractorId;
    }

    public void setJobsContractorId(int jobsContractorId) 
    {
        this.jobsContractorId = jobsContractorId;
    }
}
