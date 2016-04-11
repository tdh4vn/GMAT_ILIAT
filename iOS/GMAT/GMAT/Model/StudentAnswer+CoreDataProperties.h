//
//  StudentAnswer+CoreDataProperties.h
//  GMAT
//
//  Created by Trung Đức on 4/11/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

#import "StudentAnswer.h"

NS_ASSUME_NONNULL_BEGIN

@interface StudentAnswer (CoreDataProperties)

@property (nullable, nonatomic, retain) NSNumber *answerChoiceIdx;
@property (nullable, nonatomic, retain) NSNumber *result;
@property (nullable, nonatomic, retain) Question *question;

@end

NS_ASSUME_NONNULL_END
