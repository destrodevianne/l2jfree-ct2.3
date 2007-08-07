/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package net.sf.l2j.gameserver.communitybbs.model.forum;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

// Generated 19 f�vr. 2007 22:07:55 by Hibernate Tools 3.2.0.beta8

/**
 * Topic generated by hbm2java
 */
public class Topic implements java.io.Serializable
{

    // Fields    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1673919324213673955L;
    private Set<Posts> postses = new HashSet<Posts>(0);
    
    private int topicId;
    private int topicForumId;
    private String topicName;
    private BigDecimal topicDate;
    private String topicOwnername;
    private int topicOwnerid;
    private int topicType;
    private int topicReply;    
    

    // Constructors

    /** default constructor */
    public Topic()
    {
    }

    /** full constructor */
    public Topic(int _topicId, int _topicForumId, String _topicName, BigDecimal _topicDate,
                 String _topicOwnername, int _topicOwnerid, int _topicType, int _topicReply)
    {
        this.topicId = _topicId;
        this.topicForumId = _topicForumId;
        this.topicName = _topicName;
        this.topicDate = _topicDate;
        this.topicOwnername = _topicOwnername;
        this.topicOwnerid = _topicOwnerid;
        this.topicType = _topicType;
        this.topicReply = _topicReply;
    }

    // Property accessors
    public int getTopicId()
    {
        return this.topicId;
    }

    public void setTopicId(int _topicId)
    {
        this.topicId = _topicId;
    }

    public int getTopicForumId()
    {
        return this.topicForumId;
    }

    public void setTopicForumId(int _topicForumId)
    {
        this.topicForumId = _topicForumId;
    }

    public String getTopicName()
    {
        return this.topicName;
    }

    public void setTopicName(String _topicName)
    {
        this.topicName = _topicName;
    }

    public BigDecimal getTopicDate()
    {
        return this.topicDate;
    }

    public void setTopicDate(BigDecimal _topicDate)
    {
        this.topicDate = _topicDate;
    }

    public String getTopicOwnername()
    {
        return this.topicOwnername;
    }

    public void setTopicOwnername(String _topicOwnername)
    {
        this.topicOwnername = _topicOwnername;
    }

    public int getTopicOwnerid()
    {
        return this.topicOwnerid;
    }

    public void setTopicOwnerid(int _topicOwnerid)
    {
        this.topicOwnerid = _topicOwnerid;
    }

    public int getTopicType()
    {
        return this.topicType;
    }

    public void setTopicType(int _topicType)
    {
        this.topicType = _topicType;
    }

    public int getTopicReply()
    {
        return this.topicReply;
    }

    public void setTopicReply(int _topicReply)
    {
        this.topicReply = _topicReply;
    }

    public Set<Posts> getPostses()
    {
        return this.postses;
    }

    public void setPostses(Set<Posts> _postses)
    {
        this.postses = _postses;
    }    
    /**
     * @return the hashcode of the object
     */
    public int hashCode() 
    {
        return new HashCodeBuilder(17,37)
                        .append(this.topicOwnerid)
                        .append(this.topicName)
                        .append(this.topicDate)
                        .append(this.topicForumId)
                        .append(this.topicType)
                        .toHashCode();
    }
    
    /**
     * @return true or false if the two objects are equals (not based on post id)
     * @param obj
     */
    public boolean equals(Object _obj) 
    {
        if (_obj == null) 
        {
            return false;
        }
        if (this == _obj) 
        {
            return true;
        }
        Topic rhs = (Topic) _obj;
        return new EqualsBuilder()
                        .appendSuper(super.equals(_obj))
                        .append(topicOwnerid, rhs.getTopicOwnerid())
                        .append(topicName, rhs.getTopicName())
                        .append(topicDate, rhs.getTopicDate())
                        .append(topicForumId, rhs.getTopicForumId())
                        .append(topicType, rhs.getTopicType())
                        .isEquals();        
    }
    
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }       

}
