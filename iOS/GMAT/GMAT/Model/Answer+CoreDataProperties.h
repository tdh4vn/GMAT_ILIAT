//
//  Answer+CoreDataProperties.h
//  GMAT
//
//  Created by Trung Đức on 3/21/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

#import "Answer.h"

NS_ASSUME_NONNULL_BEGIN

@interface Answer (CoreDataProperties)

@property (nullable, nonatomic, retain) NSNumber *idx;
@property (nullable, nonatomic, retain) NSString *content;
@property (nullable, nonatomic, retain) Question *question;

@end

NS_ASSUME_NONNULL_END
