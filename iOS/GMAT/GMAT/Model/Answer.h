//
//  Answer.h
//  GMAT
//
//  Created by Trung Đức on 3/21/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>

@class Question;

NS_ASSUME_NONNULL_BEGIN

@interface Answer : NSManagedObject

// Insert code here to declare functionality of your managed object subclass

- (instancetype)answerWithJson:(NSDictionary *)json;

@end

NS_ASSUME_NONNULL_END

#import "Answer+CoreDataProperties.h"
